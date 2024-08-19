package com.vemser.rest.tests.login;

import com.vemser.rest.client.LoginClient;
import com.vemser.rest.data.factory.UsuariosDataFactory;
import com.vemser.rest.model.usuarios.UsuarioResponseModel;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class LoginTest {
    private final LoginClient loginClient = new LoginClient();
    @Test
    public void testLoginComSucesso() {
        UsuarioResponseModel usuarioLogin = UsuariosDataFactory.usuarioAdminLogin();
        String email = usuarioLogin.getEmail();
        String password = usuarioLogin.getPassword();

        loginClient.login(email, password)
        .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("message", equalTo("Login realizado com sucesso"))
                .body("authorization", notNullValue());
    }

    @Test
    public void testLoginComSenhaIncorreta() {
        UsuarioResponseModel usuarioLogin = UsuariosDataFactory.usuarioAdminLogin();
        String email = usuarioLogin.getEmail();
        String password = "senhaInvalida";

        loginClient.login(email, password)
        .then()
                .log().all()
                .statusCode(401)
                .time(lessThan(3000L))
                .body("message", equalTo("Email e/ou senha inválidos"));
    }
}
