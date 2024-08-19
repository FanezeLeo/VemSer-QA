package com.vemser.rest.tests.usuarios;

import com.vemser.rest.client.UsuarioClient;
import com.vemser.rest.data.factory.UsuariosDataFactory;
import com.vemser.rest.model.usuarios.UsuariosModel;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class UsuarioPutTest {
    private final UsuarioClient usuarioClient = new UsuarioClient();

    @Test
    public void testAtualizarUsuariosComSucesso() {
        UsuariosModel usuario = UsuariosDataFactory.usuarioValido();
        String id = UsuariosDataFactory.criarUsuario();

        usuarioClient.editarUsuarios(usuario, id)
        .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("message", equalTo("Registro alterado com sucesso"))
        ;

        usuarioClient.excluirUsuario(id);
    }

    @Test
    public void testAtualizarUsuariosComEmailExistente() {

        UsuariosModel usuario = UsuariosDataFactory.usuarioComEmailExistente();
        String id = UsuariosDataFactory.criarUsuario();

        usuarioClient.editarUsuarios(usuario, id)
        .then()
                .log().all()
                .statusCode(400).time(lessThan(3000L))
                .body("message", equalTo("Este email já está sendo usado"))
        ;

        usuarioClient.excluirUsuario(id);
    }

    @Test
    public void testAtualizarUsuariosComIdIvalidoComSucesso() {
        String idInvalido = "invalido";

        UsuariosModel usuario = UsuariosDataFactory.usuarioValido();
        String id =
            usuarioClient.editarUsuarios(usuario, idInvalido)
            .then()
                    .log().all()
                    .statusCode(201)
                    .time(lessThan(3000L))
                    .body("message", equalTo("Cadastro realizado com sucesso"))
                    .body("_id", notNullValue())
                    .extract()
                    .path("_id");

        usuarioClient.excluirUsuario(id);
    }
}
