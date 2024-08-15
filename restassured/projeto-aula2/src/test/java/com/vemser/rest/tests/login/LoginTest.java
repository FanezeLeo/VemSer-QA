package com.vemser.rest.tests.login;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import com.vemser.rest.model.login.Login;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LoginTest {

    @BeforeEach
    public void setUp() {
        baseURI = "http://localhost:3000";
    }
    @Test
    public void testLoginComSucesso() {
        Login login = new Login();
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
                .statusCode(200)
                .time(lessThan(3000L))
                .body("message", equalTo("Login realizado com sucesso"))
                .body("authorization", notNullValue());
    }

    @Test
    public void testLoginComSenhaIncorreta() {
        Login login = new Login();
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
                .statusCode(401)
                .time(lessThan(3000L))
                .body("message", equalTo("Email e/ou senha inválidos"));
    }
}
