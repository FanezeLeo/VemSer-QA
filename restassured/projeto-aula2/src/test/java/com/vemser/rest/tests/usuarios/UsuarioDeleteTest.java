package com.vemser.rest.tests.usuarios;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class UsuarioDeleteTest {
    @BeforeEach
    public void setUp() {
        baseURI = "http://localhost:3000";
    }

    @Test
    public void testExcluirUsuarioComSucesso() {

        String id = "xODsbO6qTwpCIt0R";

        given()
                .log().all()
                .pathParam("id", id)
                .when()
                .delete("/usuarios/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("message", equalTo("Registro excluído com sucesso"))
        ;
    }

    @Test
    public void testExcluirUsuarioComIdInvalido() {

        String id = "invalido";

        given()
                .log().all()
                .pathParam("id", id)
                .when()
                .delete("/usuarios/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("message", equalTo("Nenhum registro excluído"))
        ;
    }
}
