package com.vemser.rest.tests.login;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

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

public class LoginTest {

    @BeforeEach
    public void setUp() {
        baseURI = "http://localhost:3000";
    }
    @Test
    public void testLoginComSucesso() {
        LoginPOJO login = new LoginPOJO();
        login.setEmail("Garett55@yahoo.com");
        login.setPassword("YgJNDqEEHTxj3N6");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(login)
                .when()
                .post("/login")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void testLoginComSenhaIncorreta() {
        LoginPOJO login = new LoginPOJO();
        login.setEmail("Garett55@yahoo.com");
        login.setPassword("incorreta");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(login)
                .when()
                .post("/login")
                .then()
                .log().all()
                .statusCode(401);
    }
}
