package com.vemser.rest.tests.produtos;

import com.vemser.rest.model.login.LoginPOJO;
import com.vemser.rest.model.produtos.ProdutoPOJO;
import com.vemser.rest.model.usuario.UsuariosPOJO;
import io.restassured.http.ContentType;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Random;

import static io.restassured.RestAssured.*;

public class ProdutosTest {

    Faker faker = new Faker(new Locale("PT-BR"));
    Random geradorBoolean = new Random();
    String token;

    @BeforeEach
    public void setUp() {
        baseURI = "http://localhost:3000";
    }

    private String obterToken() {
        LoginPOJO login = new LoginPOJO();
        login.setEmail("Gillian76@gmail.com");
        login.setPassword("O48wXF_s7IZyD5F");

        token =
                given()
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

        return token;
    }

    @Test
    public void testListarTodosProdutosComSucesso() {

        given()
                .when()
                .get("/produtos")
                .then()
                .statusCode(200)
        ;
    }

    @Test
    public void testListarProdutoPorIdComSucesso() {

        String id = "BeeJh5lz3k6kSIzA";

        given()
                .log().all()
                .pathParam("id", id)
                .when()
                .get("/produtos/{id}")
                .then()
                .log().all()
                .statusCode(200)
        ;
    }

    @Test
    public void testListarProdutoPorIdInvalido() {

        String id = "invalido";

        given()
                .log().all()
                .pathParam("id", id)
                .when()
                .get("/produtos/{id}")
                .then()
                .log().all()
                .statusCode(400)
        ;
    }

    @Test
    public void testCadastrarProdutoComSucesso() {

        ProdutoPOJO produto = new ProdutoPOJO();
        produto.setNome(faker.commerce().productName());
        produto.setPreco(faker.number().numberBetween(1, 10000));
        produto.setDescricao(faker.commerce().material());
        produto.setQuantidade(faker.number().numberBetween(1, 10000));

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", obterToken())
                .body(produto)
                .when()
                .post("/produtos")
                .then()
                .log().all()
                .statusCode(201)
        ;
    }

    @Test
    public void testCadastrarProdutoComNomeJaUtilizado() {

        ProdutoPOJO produto = new ProdutoPOJO();
        produto.setNome("Honduras");
        produto.setPreco(faker.number().numberBetween(1, 10000));
        produto.setDescricao(faker.commerce().material());
        produto.setQuantidade(faker.number().numberBetween(1, 10000));

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", obterToken())
                .body(produto)
                .when()
                .post("/produtos")
                .then()
                .log().all()
                .statusCode(400)
        ;
    }

    @Test
    public void testCadastrarProdutoComNomeEmBranco() {

        ProdutoPOJO produto = new ProdutoPOJO();
        produto.setNome("");
        produto.setPreco(faker.number().numberBetween(1, 10000));
        produto.setDescricao(faker.commerce().material());
        produto.setQuantidade(faker.number().numberBetween(1, 10000));

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", obterToken())
                .body(produto)
                .when()
                .post("/produtos")
                .then()
                .log().all()
                .statusCode(400)
        ;
    }

    @Test
    public void testAtualizarProdutosComSucesso() {

        String id = "SUeqfBUFN4q6XXUH";

        ProdutoPOJO produto = new ProdutoPOJO();
        produto.setNome(faker.commerce().productName());
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
                .statusCode(200)
        ;
    }

    @Test
    public void testAtualizarProdutosComIdInvalidoComSucesso() {

        String id = "invalido";

        ProdutoPOJO produto = new ProdutoPOJO();
        produto.setNome(faker.commerce().productName());
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
                .statusCode(201)
        ;
    }

    @Test
    public void testAtualizarProdutosComNomeJaUtilizado() {

        String id = "SUeqfBUFN4q6XXUH";

        ProdutoPOJO produto = new ProdutoPOJO();
        produto.setNome("Honduras");
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
        ;
    }

    @Test
    public void testAtualizarProdutosComNomeEmBranco() {

        String id = "SUeqfBUFN4q6XXUH";

        ProdutoPOJO produto = new ProdutoPOJO();
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
        ;
    }

    @Test
    public void testExcluirProdutosComSucesso() {

        String id = "ARMKsk6r2J0rB7QR";

        given()
                .log().all()
                .header("Authorization", obterToken())
                .pathParam("id", id)
                .when()
                .delete("/produtos/{id}")
                .then()
                .log().all()
                .statusCode(200)
        ;
    }

    @Test
    public void testExcluirProdutosComIdInvalido() {

        String id = "invalido";

        given()
                .log().all()
                .header("Authorization", obterToken())
                .pathParam("id", id)
                .when()
                .delete("/produtos/{id}")
                .then()
                .log().all()
                .statusCode(200)
        ;
    }

}
