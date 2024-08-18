package com.vemser.rest.tests.usuarios;

import com.vemser.rest.model.usuarios.Usuarios;
import io.restassured.http.ContentType;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class UsuarioPostTest {
    Faker faker = new Faker(new Locale("PT-BR"));
    Random geradorBoolean = new Random();

    @BeforeEach
    public void setUp() {
        baseURI = "http://localhost:3000";
    }

    @Test
    public void testCadastrarUsuarioComSucesso() {

        Usuarios usuario = new Usuarios();
        usuario.setNome(faker.name().firstName() + " " + faker.name().lastName());
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setPassword(faker.internet().password());
        usuario.setAdministrador(String.valueOf(geradorBoolean.nextBoolean()));

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(usuario)
        .when()
                .post("/usuarios")
        .then()
                .log().all()
                .statusCode(201)
                .time(lessThan(3000L))
                .body(matchesJsonSchemaInClasspath("schema/usuarios/cadastrar_usuarios.json"))
                .body("message", equalTo("Cadastro realizado com sucesso"))
                .body("_id", notNullValue())
        ;
    }

    @Test
    public void testCadastrarUsuarioComEmailExistente() {

        Usuarios usuario = new Usuarios();
        usuario.setNome(faker.name().firstName() + " " + faker.name().lastName());
        usuario.setEmail("Garett55@yahoo.com");
        usuario.setPassword(faker.internet().password());
        usuario.setAdministrador(String.valueOf(geradorBoolean.nextBoolean()));

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(usuario)
        .when()
                .post("/usuarios")
        .then()
                .log().all()
                .statusCode(400)
                .time(lessThan(3000L))
                .body("message", equalTo( "Este email já está sendo usado"))
        ;
    }
}
