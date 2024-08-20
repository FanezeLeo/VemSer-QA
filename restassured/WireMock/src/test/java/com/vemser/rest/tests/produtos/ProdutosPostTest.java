package com.vemser.rest.tests.produtos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.vemser.rest.client.ProdutoClient;
import com.vemser.rest.data.factory.ProdutosDataFactory;
import com.vemser.rest.model.produtos.*;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

@WireMockTest(httpPort = 8080)
public class ProdutosPostTest {
    private final ProdutoClient produtoClient = new ProdutoClient();
    ObjectMapper objectMapper = new ObjectMapper();
    ProdutosModel produto = ProdutosDataFactory.produtoValido();
    ProdutosModel produtoNomeEmBranco = ProdutosDataFactory.produtoComNomeEmBranco();

    String auth = "Bearer 123dcb321";

    @BeforeEach
    public void setup() throws Exception{
        String response = new String(Files.readAllBytes(Paths.get("src/test/resources/schema/produtos/responseCadastroProdutoWireMock.json")));


        stubFor(post(urlEqualTo("/produtos"))
                .withRequestBody(equalToJson(objectMapper.writeValueAsString(produto)))
                .withHeader("Authorization", equalTo(auth))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_CREATED)
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)
                )
        );

        stubFor(post(urlEqualTo("/produtos"))
                .withRequestBody(equalToJson(objectMapper.writeValueAsString(produtoNomeEmBranco)))
                .withHeader("Authorization", equalTo(auth))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SC_BAD_REQUEST)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"nome\": \"nome não pode ficar em branco\"}")
                )
        );
    }

    @Test
    public void testCadastrarProdutoComSucesso(){
        produtoClient.cadastrarProdutos(produto, auth)
                .then()
                .log().all()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath("schema/produtos/cadastrar_produtso.json"))
                .body("_id", notNullValue())
                .extract()
                .path("_id")
        ;
    }

    @Test
    public void testCadastrarProdutoComNomeEmBranco() {
        produtoClient.cadastrarProdutos(produtoNomeEmBranco, auth)
        .then()
                .log().all()
                .statusCode(400);
    }
}
