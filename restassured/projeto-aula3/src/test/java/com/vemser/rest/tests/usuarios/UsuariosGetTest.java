package com.vemser.rest.tests.usuarios;

import com.vemser.rest.client.UsuarioClient;
import com.vemser.rest.data.factory.UsuariosDataFactory;
import org.junit.jupiter.api.Test;


import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class UsuariosGetTest {
    private final UsuarioClient usuarioClient = new UsuarioClient();

    @Test
    public void testListarTodosUsuariosComSucesso() {

        usuarioClient.getUsuarios()
        .then()
                .statusCode(200)
                .time(lessThan(3000L))
                .body(matchesJsonSchemaInClasspath("schema/usuarios/listar_todos_usuarios.json"))
                .body("quantidade", greaterThan(0))
                .body("usuarios[0]", notNullValue())
        ;
    }

    @Test
    public void testListarUsuariosPorNomeComSucesso() {

        String nome = UsuariosDataFactory.nomeExistente();

        usuarioClient.getUsuariosPorNome(nome)
        .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("quantidade", greaterThan(0))
                .body("usuarios[0].nome", equalTo(nome))
        ;
    }

    @Test
    public void testListarUsuariosPorNomeInvalido() {

        String nome = "Nome Invalido";

        usuarioClient.getUsuariosPorNome(nome)
        .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("quantidade", equalTo(0))
                .body("usuarios", empty())
        ;
    }

    @Test
    public void testBuscarUsuarioPorIDComSucesso() {

        String id = UsuariosDataFactory.idExistente();

        usuarioClient.getUsuario(id)
        .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(3000L))
                .body(matchesJsonSchemaInClasspath("schema/usuarios/usuarios_por_id.json"))
                .body("nome", notNullValue())
                .body("email", notNullValue())
                .body("password", notNullValue())
                .body("administrador", notNullValue())
                .body("_id", equalTo(id))
        ;
    }

    @Test
    public void testBuscarUsuarioPorIDInvalido() {

        String id = "invalido";

        usuarioClient.getUsuario(id)
        .then()
                .log().all()
                .statusCode(400)
                .time(lessThan(3000L))
                .body("message", equalTo("Usuário não encontrado"))
        ;
    }
}
