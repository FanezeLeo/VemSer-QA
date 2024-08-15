package com.vemser.rest.tests.produtos;

import com.vemser.rest.model.login.Login;
import com.vemser.rest.model.produtos.Produto;
import com.vemser.rest.model.produtos.ProdutoCreateResponse;
import com.vemser.rest.model.produtos.ProdutoMessageResponse;
import io.restassured.http.ContentType;
import net.datafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ProdutosPutTest {
    Faker faker = new Faker(new Locale("PT-BR"));

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
    public void testAtualizarProdutosComSucesso() {

        String id = "SUeqfBUFN4q6XXUH";

        Produto produto = new Produto();
        produto.setNome(faker.commerce().productName());
        produto.setPreco(faker.number().numberBetween(1, 10000));
        produto.setDescricao(faker.commerce().material());
        produto.setQuantidade(faker.number().numberBetween(1, 10000));

        ProdutoMessageResponse response =
                given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .header("Authorization", obterToken())
                        .body(produto)
                        .pathParam("id", id)
                        .when()
                        .put("/produtos/{id}")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .as(ProdutoMessageResponse.class)
                ;
        Assertions.assertAll("response",
                ()-> Assertions.assertEquals(response.getMessage(), "Registro alterado com sucesso"));
    }

    @Test
    public void testAtualizarProdutosComIdInvalidoComSucesso() {

        String id = "invalido";

        Produto produto = new Produto();
        produto.setNome(faker.commerce().productName());
        produto.setPreco(faker.number().numberBetween(1, 10000));
        produto.setDescricao(faker.commerce().material());
        produto.setQuantidade(faker.number().numberBetween(1, 10000));

        ProdutoCreateResponse response =
                given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .header("Authorization", obterToken())
                        .body(produto)
                        .pathParam("id", id)
                        .when()
                        .put("/produtos/{id}")
                        .then()
                        .log().all()
                        .statusCode(201)
                        .extract()
                        .as(ProdutoCreateResponse.class)
                ;
        Assertions.assertAll("response",
                ()-> Assertions.assertEquals(response.getMessage(), "Cadastro realizado com sucesso"),
                ()-> Assertions.assertNotNull(response.get_id()));
    }

    @Test
    public void testAtualizarProdutosComNomeJaUtilizado() {

        String id = "SUeqfBUFN4q6XXUH";

        Produto produto = new Produto();
        produto.setNome("Honduras");
        produto.setPreco(faker.number().numberBetween(1, 10000));
        produto.setDescricao(faker.commerce().material());
        produto.setQuantidade(faker.number().numberBetween(1, 10000));

        ProdutoMessageResponse response =
                given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .header("Authorization", obterToken())
                        .body(produto)
                        .pathParam("id", id)
                        .when()
                        .put("/produtos/{id}")
                        .then()
                        .log().all()
                        .statusCode(400)
                        .extract()
                        .as(ProdutoMessageResponse.class)
                ;
        Assertions.assertAll("response",
                ()-> Assertions.assertEquals(response.getMessage(), "Já existe produto com esse nome"));
    }

    @Test
    public void testAtualizarProdutosComNomeEmBranco() {

        String id = "SUeqfBUFN4q6XXUH";

        Produto produto = new Produto();
        produto.setNome("");
        produto.setPreco(faker.number().numberBetween(1, 10000));
        produto.setDescricao(faker.commerce().material());
        produto.setQuantidade(faker.number().numberBetween(1, 10000));

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", obterToken())
                .body(produto)
                .pathParam("id", id)
                .when()
                .put("/produtos/{id}")
                .then()
                .log().all()
                .statusCode(400)
                .time(lessThan(3000L))
                .body("nome", equalTo("nome não pode ficar em branco"));

    }
}
