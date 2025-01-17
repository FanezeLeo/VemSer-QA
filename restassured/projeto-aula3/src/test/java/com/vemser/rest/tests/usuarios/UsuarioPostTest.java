package com.vemser.rest.tests.usuarios;

import com.vemser.rest.client.UsuarioClient;
import com.vemser.rest.data.factory.UsuariosDataFactory;
import com.vemser.rest.data.provider.ProdutosDataProvider;
import com.vemser.rest.data.provider.UsuariosDataProvider;
import com.vemser.rest.model.produtos.ProdutosModel;
import com.vemser.rest.model.usuarios.UsuariosModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class UsuarioPostTest {
    UsuarioClient usuarioClient = new UsuarioClient();

    @Test
    public void testCadastrarUsuarioComSucesso() {
        UsuariosModel usuario = UsuariosDataFactory.usuarioValido();

        String idDelete =
                usuarioClient.cadastrarUsuarios(usuario)
                .then()
                        .log().all()
                        .statusCode(201)
                        .time(lessThan(3000L))
                        .body(matchesJsonSchemaInClasspath("schema/usuarios/cadastrar_usuarios.json"))
                        .body("message", equalTo("Cadastro realizado com sucesso"))
                        .body("_id", notNullValue())
                        .extract()
                        .path("_id")
        ;

        usuarioClient.excluirUsuario(idDelete);

    }

    @Test
    public void testCadastrarUsuarioComEmailExistente() {

        UsuariosModel usuario = UsuariosDataFactory.usuarioComEmailExistente();

        usuarioClient.cadastrarUsuarios(usuario)
        .then()
                .log().all()
                .statusCode(400)
                .time(lessThan(3000L))
                .body("message", equalTo( "Este email já está sendo usado"))



        ;
    }

    @ParameterizedTest
    @MethodSource("providerUsuariosData")
    public void testProdutoCamposEmBranco(UsuariosModel usuario, String campo, String message){
        Response response = usuarioClient.cadastrarUsuarios(usuario);
        Assertions.assertEquals(400, response.getStatusCode(), "Status code deve ser 400");
    }

    private static Stream<Arguments> providerUsuariosData(){
        return UsuariosDataProvider.usuarioDataProvider();
    }
}
