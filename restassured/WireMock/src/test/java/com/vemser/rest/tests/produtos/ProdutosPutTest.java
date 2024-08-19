package com.vemser.rest.tests.produtos;

import com.vemser.rest.client.ProdutoClient;
import com.vemser.rest.data.factory.ProdutosDataFactory;

import com.vemser.rest.model.produtos.ProdutosModel;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class ProdutosPutTest {
    private final ProdutoClient produtoClient = new ProdutoClient();
    private M
    @Test
    public void testAtualizarProdutosComSucesso() {

        String id = ProdutosDataFactory.criarProduto();
        ProdutosModel produto = ProdutosDataFactory.produtoValido();
        produtoClient.editarProduto(produto, id)
        .then()
                .log().all()
                .statusCode(200)
                .body("message", equalTo("Registro alterado com sucesso"));

        produtoClient.excluirProduto(id);
    }

    @Test
    public void testAtualizarProdutosComIdInvalidoComSucesso() {

        String id = "invalido";
        ProdutosModel produto = ProdutosDataFactory.produtoValido();
        String idExcluir =
            produtoClient.editarProduto(produto, id)
            .then()
                    .log().all()
                    .statusCode(201)
                    .body("message", equalTo("Cadastro realizado com sucesso"))
                    .body("_id", notNullValue())
                    .extract()
                    .path("_id");
        produtoClient.excluirProduto(idExcluir);
    }

    @Test
    public void testAtualizarProdutosComNomeJaUtilizado() {

        String id = ProdutosDataFactory.criarProduto();
        ProdutosModel produto = ProdutosDataFactory.produtoComNomeExistente();

        produtoClient.editarProduto(produto, id)
        .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo("Já existe produto com esse nome"));
        produtoClient.excluirProduto(id);

    }

    @Test
    public void testAtualizarProdutosComNomeEmBranco() {

        String id = ProdutosDataFactory.criarProduto();
        ProdutosModel produto = ProdutosDataFactory.produtoComNomeEmBranco();

        produtoClient.editarProduto(produto, id)
        .then()
                .log().all()
                .statusCode(400)
                .body("nome", equalTo("nome não pode ficar em branco"));

        produtoClient.excluirProduto(id);

    }
}
