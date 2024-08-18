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
import static org.hamcrest.Matchers.*;

public class UsuarioPutTest {
    Faker faker = new Faker(new Locale("PT-BR"));
    Random geradorBoolean = new Random();

    @BeforeEach
    public void setUp() {
        baseURI = "http://localhost:3000";
    }

    @Test
    public void testAtualizarUsuariosComSucesso() {

        String id = "0uxuPY0cbmQhpEz1";

        Usuarios usuario = new Usuarios();
        usuario.setNome(faker.name().firstName() + " " + faker.name().lastName());
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setPassword(faker.internet().password());
        usuario.setAdministrador(String.valueOf(geradorBoolean.nextBoolean()));

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(usuario)
                .pathParam("id", id)
        .when()
                .put("/usuarios/{id}")
        .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("message", equalTo("Registro alterado com sucesso"))
        ;
    }

    @Test
    public void testAtualizarUsuariosComEmailExistente() {

        String id = "0uxuPY0cbmQhpEz1";

        Usuarios usuario = new Usuarios();
        usuario.setNome(faker.name().firstName() + " " + faker.name().lastName());
        usuario.setEmail("Garett55@yahoo.com");
        usuario.setPassword(faker.internet().password());
        usuario.setAdministrador(String.valueOf(geradorBoolean.nextBoolean()));

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(usuario)
                .pathParam("id", id)
        .when()
                .put("/usuarios/{id}")
        .then()
                .log().all()
                .statusCode(400).time(lessThan(3000L))
                .body("message", equalTo("Este email já está sendo usado"))
        ;
    }

    @Test
    public void testAtualizarUsuariosComIdIvalidoComSucesso() {

        String id = "invalido";

        Usuarios usuario = new Usuarios();
        usuario.setNome(faker.name().firstName() + " " + faker.name().lastName());
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setPassword(faker.internet().password());
        usuario.setAdministrador(String.valueOf(geradorBoolean.nextBoolean()));

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(usuario)
                .pathParam("id", id)
        .when()
                .put("/usuarios/{id}")
        .then()
                .log().all()
                .statusCode(201)
                .time(lessThan(3000L))
                .body("message", equalTo("Cadastro realizado com sucesso"))
                .body("_id", notNullValue())
        ;
    }
}
