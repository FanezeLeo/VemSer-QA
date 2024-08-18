package com.vemser.rest.tests.produtos;

import com.vemser.rest.client.ProdutoClient;
import com.vemser.rest.data.factory.ProdutosDataFactory;

import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;

public class ProdutosDeleteTest {
    private final ProdutoClient produtoClient = new ProdutoClient();

    @Test
    public void testExcluirProdutosComSucesso() {

        String id = ProdutosDataFactory.idExistente();

                produtoClient.excluirProduto(id)
                .then()
                        .log().all()
                        .statusCode(200)
                        .body("message", equalTo("Registro excluído com sucesso"));
    }

    @Test
    public void testExcluirProdutosComIdInvalido() {

        String id = "invalido";

                produtoClient.excluirProduto(id)
                .then()
                        .log().all()
                        .statusCode(200)
                        .body("message", equalTo("Nenhum registro excluído"));
    }
}
