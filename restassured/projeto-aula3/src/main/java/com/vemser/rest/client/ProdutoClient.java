package com.vemser.rest.client;


import com.vemser.rest.data.factory.LoginDataFactory;
import com.vemser.rest.data.factory.UsuariosDataFactory;
import com.vemser.rest.model.produtos.ProdutosModel;
import com.vemser.rest.model.produtos.ProdutosResponseModel;
import com.vemser.rest.model.usuarios.UsuarioResponseModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;


public class ProdutoClient extends BaseClient{
    private final String PRODUTOS = "/produtos";

    private String auth(){
        UsuarioResponseModel usuarioLogin = UsuariosDataFactory.usuarioAdminLogin();
        String email = usuarioLogin.getEmail();
        String password = usuarioLogin.getPassword();
        return LoginDataFactory.auth(email, password);
    }

    public Response cadastrarProdutos(ProdutosModel produto) {
        String auth = auth();
        return
                given()
                        .spec(super.set())
                        .header("Authorization", auth)
                        .contentType(ContentType.JSON)
                        .body(produto)
                .when()
                        .post(PRODUTOS);

    }

    public Response editarProduto(ProdutosModel produto, String id) {
        String auth = auth();

        return
                given()
                        .header("Authorization", auth)
                        .spec(super.set())
                        .contentType(ContentType.JSON)
                        .body(produto)
                        .pathParam("id", id)
                        .when()
                        .put(PRODUTOS + "/{id}");

    }

    public Response excluirProduto(String id){
        String auth = auth();

        return
                given()
                        .header("Authorization", auth)
                        .spec(super.set())
                        .pathParam("id", id)
                .when()
                        .delete(PRODUTOS + "/{id}");
    }

    public Response getProdutos(){
        return
                given()
                        .spec(super.set())
                        .when()
                        .get(PRODUTOS);
    }

    public Response getProdutoPorNome(String nome){
        return
                given()
                        .spec(super.set())
                        .queryParam("nome", nome)
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
