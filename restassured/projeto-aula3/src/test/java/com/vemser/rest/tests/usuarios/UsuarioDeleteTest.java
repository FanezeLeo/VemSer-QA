package com.vemser.rest.tests.usuarios;

import com.vemser.rest.client.UsuarioClient;
import com.vemser.rest.data.factory.UsuariosDataFactory;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class UsuarioDeleteTest {
    UsuarioClient usuarioClient = new UsuarioClient();

    @Test
    public void testExcluirUsuarioComSucesso() {
        String id = UsuariosDataFactory.criarUsuario();

        usuarioClient.excluirUsuario(id)
        .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("message", equalTo("Registro excluído com sucesso"))
        ;
    }

    @Test
    public void testExcluirUsuarioComIdInvalido() {

        String id = "invalido";

        usuarioClient.excluirUsuario(id)
        .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("message", equalTo("Nenhum registro excluído"))
        ;
    }
}
