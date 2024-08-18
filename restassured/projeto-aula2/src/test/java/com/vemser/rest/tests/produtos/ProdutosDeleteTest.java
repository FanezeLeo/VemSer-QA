package com.vemser.rest.tests.produtos;

import com.vemser.rest.model.login.Login;
import com.vemser.rest.model.produtos.ProdutoMessageResponse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class ProdutosDeleteTest {

    @BeforeEach
    public void setUp() {
        baseURI = "http://localhost:3000";
    }

    private String obterToken() {
        Login login = new Login();
        login.setEmail("Gillian76@gmail.com");
        login.setPassword("O48wXF_s7IZyD5F");

        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(login)
        .when()
                .post("/login")
        .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("authorization");
    }

    @Test
    public void testExcluirProdutosComSucesso() {

        String id = "EUm6b5R78cuNrVSM";

        ProdutoMessageResponse response =
                given()
                        .log().all()
                        .header("Authorization", obterToken())
                        .pathParam("id", id)
                .when()
                        .delete("/produtos/{id}")
                .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .as(ProdutoMessageResponse.class)
                ;
        Assertions.assertEquals(response.getMessage(), "Registro excluído com sucesso");
    }

    @Test
    public void testExcluirProdutosComIdInvalido() {

        String id = "invalido";

        ProdutoMessageResponse response =
                given()
                        .log().all()
                        .header("Authorization", obterToken())
                        .pathParam("id", id)
                .when()
                        .delete("/produtos/{id}")
                .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .as(ProdutoMessageResponse.class);
        Assertions.assertEquals(response.getMessage(), "Nenhum registro excluído");
    }
}
