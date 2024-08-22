package com.vemser.rest.client;

import com.vemser.rest.model.produtos.ProdutosModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProdutoWireMockClient extends BaseWireMockClient{
    private final String PRODUTOS = "/produtos";

    public Response cadastrarProdutos(ProdutosModel produto, String auth) {
        return
                given()
                        .spec(super.set())
                        .header("Authorization", auth)
                        .contentType(ContentType.JSON)
                        .body(produto)
                        .when()
                        .post(PRODUTOS);

    }

    public Response getProdutos(){
        return
                given()
                        .spec(super.set())
                        .when()
                        .get(PRODUTOS);
    }

    public Response getProduto(String id){
        return
                given()
                        .spec(super.set())
                        .pathParam("id", id)
                        .when()
                        .get(PRODUTOS + "/{id}");
    }
}
