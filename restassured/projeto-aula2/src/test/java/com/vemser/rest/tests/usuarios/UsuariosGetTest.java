package com.vemser.rest.tests.usuarios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class UsuariosGetTest {
    @BeforeEach
    public void setUp() {
        baseURI = "http://localhost:3000";
    }

    @Test
    public void testListarTodosUsuariosComSucesso() {

        given()
                .when()
                .get("/usuarios")
                .then()
                .statusCode(200)
                .time(lessThan(3000L))
                .body(matchesJsonSchemaInClasspath("schema/usuarios/listar_todos_usuarios.json"))
                .body("quantidade", greaterThan(0))
                .body("usuarios[0]", notNullValue())
        ;
    }

    @Test
    public void testListarUsuariosPorNomeComSucesso() {

        String nome = "Dennis Reichel";

        given()
                .log().all()
                .queryParam("nome", nome)
                .when()
                .get("/usuarios")
                .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("quantidade", greaterThan(0))
                .body("usuarios[0].nome", equalTo(nome))
        ;
    }

    @Test
    public void testListarUsuariosPorNomeInvalido() {

        String nome = "Nome Invalido";

        given()
                .log().all()
                .queryParam("nome", nome)
                .when()
                .get("/usuarios")
                .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("quantidade", equalTo(0))
                .body("usuarios", empty())
        ;
    }

    @Test
    public void testBuscarUsuarioPorIDComSucesso() {

        String id = "ARMKsk6r2J0rB7QR";

        given()
                .log().all()
                .pathParam("id", id)
                .when()
                .get("/usuarios/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(3000L))
                .body(matchesJsonSchemaInClasspath("schema/usuarios/usuarios_por_id.json"))
                .body("nome", notNullValue())
                .body("email", notNullValue())
                .body("password", notNullValue())
                .body("administrador", notNullValue())
                .body("_id", equalTo(id))
        ;
    }

    @Test
    public void testBuscarUsuarioPorIDInvalido() {

        String id = "invalido";

        given()
                .log().all()
                .pathParam("id", id)
                .when()
                .get("/usuarios/{id}")
                .then()
                .log().all()
                .statusCode(400)
                .time(lessThan(3000L))
                .body("message", equalTo("Usuário não encontrado"))
        ;
    }
}
