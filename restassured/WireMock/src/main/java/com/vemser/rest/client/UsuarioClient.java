package com.vemser.rest.client;

import com.vemser.rest.model.usuarios.UsuariosModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;


public class UsuarioClient extends BaseClient{
    private final String USUARIOS = "/usuarios";

    public Response cadastrarUsuarios(UsuariosModel usuario) {
        return
                given()
                        .spec(super.set())
                        .contentType(ContentType.JSON)
                        .body(usuario)
                .when()
                        .post(USUARIOS);

    }

    public Response editarUsuarios(UsuariosModel usuario, String id) {
        return
                given()
                        .spec(super.set())
                        .contentType(ContentType.JSON)
                        .body(usuario)
                        .pathParam("id", id)
                .when()
                        .put(USUARIOS + "/{id}");

    }

    public Response excluirUsuario(String id){
        return
            given()
                    .spec(super.set())
                    .pathParam("id", id)
            .when()
                    .delete(USUARIOS + "/{id}");
    }

    public Response getUsuarios(){

        return
            given()
                    .spec(super.set())
            .when()
                    .get(USUARIOS);
    }

    public Response getUsuariosPorNome(String nome){
        return
                given()
                        .spec(super.set())
                        .queryParam("nome", nome)
                .when()
                        .get(USUARIOS);
    }

    public Response getUsuario(String id){
        return
                given()
                        .spec(super.set())
                        .pathParam("id", id)
                .when()
                        .get(USUARIOS + "/{id}");
    }
}
