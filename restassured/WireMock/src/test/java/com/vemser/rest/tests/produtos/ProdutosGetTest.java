package com.vemser.rest.tests.produtos;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.vemser.rest.client.ProdutoClient;
import com.vemser.rest.data.factory.ProdutosDataFactory;
import com.vemser.rest.model.produtos.ProdutosResponseModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

@WireMockTest(httpPort=8080)
public class ProdutosGetTest {
    private final ProdutoClient produtoClient = new ProdutoClient();

    private String id = "BeeJh5lz3k6kSIzA";
    private String id2 = "test";

    @BeforeEach
    public void setup() throws Exception{
        String responseApi = new String(Files.readAllBytes(Paths.get("src/test/resources/schema/produtos/getProdutosWireMock.json")));
        String responseApi2 = new String(Files.readAllBytes(Paths.get("src/test/resources/schema/produtos/getProdutoPorIdWireMock.json")));


        stubFor(get(urlEqualTo("/produtos"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseApi)
                )
        );

        stubFor(get(urlEqualTo("/produtos/" + id))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseApi2)
                )
        );

        stubFor(get(urlEqualTo("/produtos/" + id2))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_BAD_REQUEST)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\": \"ID de produto inválido\"}")
                )
        );
    }
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
        produtoClient.getProduto(id2)
        .then()
                .log().all()
                .statusCode(400);
    }

}
