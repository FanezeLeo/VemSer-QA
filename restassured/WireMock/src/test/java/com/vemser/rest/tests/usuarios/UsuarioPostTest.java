package com.vemser.rest.tests.usuarios;

import com.vemser.rest.client.UsuarioClient;
import com.vemser.rest.data.factory.UsuariosDataFactory;
import com.vemser.rest.model.usuarios.UsuariosModel;
import org.junit.jupiter.api.Test;

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
}
