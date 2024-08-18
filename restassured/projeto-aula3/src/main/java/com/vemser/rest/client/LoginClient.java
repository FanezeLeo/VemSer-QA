package com.vemser.rest.client;

import com.vemser.rest.model.LoginModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginClient extends BaseClient{
    public Response login(String email, String password) {

        LoginModel login = new LoginModel(email, password);
        return
                given()
                        .spec(super.set())
                        .contentType(ContentType.JSON)
                        .body(login)
                        .when()
                        .post("/login");

    }
}
