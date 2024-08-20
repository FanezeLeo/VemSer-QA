package com.vemser.rest.tests.produtos;

import com.vemser.rest.client.ProdutoClient;
import com.vemser.rest.data.factory.ProdutosDataFactory;
import com.vemser.rest.data.provider.ProdutosDataProvider;
import com.vemser.rest.model.produtos.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.*;

public class ProdutosPostTest {
    private final ProdutoClient produtoClient = new ProdutoClient();

    @Test
    public void testCadastrarProdutoComSucesso(){
        ProdutosModel produto = ProdutosDataFactory.produtoValido();

        String id =
                produtoClient.cadastrarProdutos(produto)
                .then()
                        .log().all()
                        .statusCode(201)
                        .body(matchesJsonSchemaInClasspath("schema/produtos/cadastrar_produtso.json"))
                        .body("message", equalTo("Cadastro realizado com sucesso"))
                        .body("_id", notNullValue())
                        .extract()
                        .path("_id")
                ;
        produtoClient.excluirProduto(id);
    }

    @Test
    public void testCadastrarProdutoComNomeJaUtilizado() {
        ProdutosModel produto = ProdutosDataFactory.produtoComNomeExistente();
        produtoClient.cadastrarProdutos(produto)
        .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo("Já existe produto com esse nome"));
    }

    @Test
    public void testCadastrarProdutoComNomeEmBranco() {
        ProdutosModel produto = ProdutosDataFactory.produtoComNomeEmBranco();
        produtoClient.cadastrarProdutos(produto)
        .then()
                .log().all()
                .statusCode(400)
                .body("nome", equalTo("nome não pode ficar em branco"));
    }

    @ParameterizedTest
    @MethodSource("providerProdutosData")
    public void testProdutoCamposEmBranco(ProdutosModel produtos, String campo, String message){
        Response response = produtoClient.cadastrarProdutos(produtos);

        String responseBody = response.getBody().asString();
        String mensagemErro = from(responseBody).getString(campo);
                Assertions.assertEquals(mensagemErro, message, "A mensagem deveria ser: " + message);
    }

    private static Stream<Arguments> providerProdutosData(){
        return ProdutosDataProvider.produtosDataProvider();
    }
}
