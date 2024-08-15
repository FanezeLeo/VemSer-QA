package com.vemser.rest.tests.produtos;

import com.vemser.rest.model.produtos.ProdutoListarProdutos;
import com.vemser.rest.model.produtos.ProdutoMessageResponse;
import com.vemser.rest.model.produtos.ProdutoResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ProdutosGetTest {

    @BeforeEach
    public void setUp() {
        baseURI = "http://localhost:3000";
    }

    @Test
    public void testListarTodosProdutosComSucesso() {
        ProdutoListarProdutos response =
                given()
                        .log().all()
                        .when()
                        .get("/produtos")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body(matchesJsonSchemaInClasspath("schema/produtos/listar_todos_produtos.json"))
                        .extract()
                        .as(ProdutoListarProdutos.class)
                ;
        Assertions.assertAll("response",
                () -> Assertions.assertNotNull(response.getProdutos()),
                () -> Assertions.assertTrue(response.getQuantidade() > 0));
    }

    @Test
    public void testListarProdutoPorIdComSucesso() {

        String id = "BeeJh5lz3k6kSIzA";

        ProdutoResponse response =
                given()
                        .log().all()
                        .pathParam("id", id)
                        .when()
                        .get("/produtos/{id}")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body(matchesJsonSchemaInClasspath("schema/produtos/listar_produtos_por_id.json"))
                        .extract()
                        .as(ProdutoResponse.class)
                ;
        Assertions.assertAll("response",
                () -> Assertions.assertEquals(response.getNome(), "Logitech Mouse Gamer"),
                () -> Assertions.assertEquals(response.getPreco(), 40),
                () -> Assertions.assertEquals(response.getDescricao(), "Mouse"),
                () -> Assertions.assertEquals(response.getQuantidade(), 31),
                () -> Assertions.assertEquals(response.getId(), "BeeJh5lz3k6kSIzA"));
    }

    @Test
    public void testListarProdutoPorIdInvalido() {

        String id = "invalido";

        ProdutoMessageResponse response =
                given()
                        .log().all()
                        .pathParam("id", id)
                        .when()
                        .get("/produtos/{id}")
                        .then()
                        .log().all()
                        .statusCode(400)
                        .extract()
                        .as(ProdutoMessageResponse.class)
                ;

        Assertions.assertAll("response",
                () -> Assertions.assertEquals(response.getMessage(), "Produto não encontrado"));
    }

}
