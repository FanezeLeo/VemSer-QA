package com.vemser.rest.tests.usuarios;

import com.vemser.rest.model.usuario.UsuariosPOJO;
import io.restassured.http.ContentType;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Random;

import static io.restassured.RestAssured.*;

public class UsuariosTest {

    Faker faker = new Faker(new Locale("PT-BR"));
    Random geradorBoolean = new Random();

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
        ;
    }

    @Test
    public void testListarUsuariosPorNomeComSucesso() {

        String nome = "Luiz Henrique Peres";

        given()
                .log().all()
                .queryParam("nome", nome)
        .when()
                .get("/usuarios")
        .then()
                .log().all()
                .statusCode(200)
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
        ;
    }

    @Test
    public void testBuscarUsuarioPorIDComSucesso() {

        String id = "11AJfgqAoUkSIWni";

        given()
                .log().all()
                .pathParam("id", id)
        .when()
                .get("/usuarios/{id}")
        .then()
                .log().all()
                .statusCode(200)
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
        ;
    }

    @Test
    public void testCadastrarUsuarioComSucesso() {

        UsuariosPOJO usuario = new UsuariosPOJO();
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
        ;
    }

    @Test
    public void testCadastrarUsuarioComEmailExistente() {

        UsuariosPOJO usuario = new UsuariosPOJO();
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
        ;
    }

    @Test
    public void testAtualizarUsuariosComSucesso() {

        String id = "0uxuPY0cbmQhpEz1";

        UsuariosPOJO usuario = new UsuariosPOJO();
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
        ;
    }

    @Test
    public void testAtualizarUsuariosComEmailExistente() {

        String id = "0uxuPY0cbmQhpEz1";

        UsuariosPOJO usuario = new UsuariosPOJO();
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
                .statusCode(400)
        ;
    }

    @Test
    public void testAtualizarUsuariosComIdIvalidoComSucesso() {

        String id = "invalido";

        UsuariosPOJO usuario = new UsuariosPOJO();
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
        ;
    }

    @Test
    public void testExcluirUsuarioComSucesso() {

        String id = "dEV1fk1klY3pl28P";

        given()
                .log().all()
                .pathParam("id", id)
        .when()
                .delete("/usuarios/{id}")
        .then()
                .log().all()
                .statusCode(200)
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
        ;
    }

}
