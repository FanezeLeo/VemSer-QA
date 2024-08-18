package com.vemser.rest.tests.produtos;

import com.vemser.rest.client.ProdutoClient;
import com.vemser.rest.data.factory.ProdutosDataFactory;
import com.vemser.rest.model.produtos.ProdutosResponseModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class ProdutosGetTest {
    private final ProdutoClient produtoClient = new ProdutoClient();

    @Test
    public void testListarTodosProdutosComSucesso() {
        produtoClient.getProdutos()
            .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schema/produtos/listar_todos_produtos.json"))
                .body("quantidade", greaterThan(0))
                .body("produtos[0]", notNullValue());
    }

    @Test
    public void testListarProdutoPorIdComSucesso() {

        String id = ProdutosDataFactory.idExistente();

        ProdutosResponseModel response =
                produtoClient.getProduto(id)
                .then()
                        .log().all()
                        .statusCode(200)
                        .body(matchesJsonSchemaInClasspath("schema/produtos/listar_produtos_por_id.json"))
                        .extract()
                        .as(ProdutosResponseModel.class)
                ;
        Assertions.assertAll("response",
                () -> Assertions.assertEquals(response.getNome(), ProdutosDataFactory.nomeExistente()),
                () -> Assertions.assertEquals(response.getPreco(), ProdutosDataFactory.precoExistente()),
                () -> Assertions.assertEquals(response.getDescricao(), ProdutosDataFactory.descricaoExistente()),
                () -> Assertions.assertEquals(response.getQuantidade(), ProdutosDataFactory.quantidadeExistente()),
                () -> Assertions.assertEquals(response.getId(), ProdutosDataFactory.idExistente()));
    }

    @Test
    public void testListarProdutoPorIdInvalido() {

        String id = "invalido";

        produtoClient.getProduto(id)
            .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo("Produto não encontrado"))
        ;
    }

}
