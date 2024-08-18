package com.vemser.rest.data.factory;

import com.vemser.rest.client.UsuarioClient;
import com.vemser.rest.model.usuarios.UsuarioResponseModel;
import com.vemser.rest.model.usuarios.UsuariosModel;
import com.vemser.rest.model.produtos.ProdutosResponseModel;
import net.datafaker.Faker;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class UsuariosDataFactory {

    private static final UsuarioClient usuariosClient = new UsuarioClient();

    private static Faker faker = new Faker(new Locale("PT-BR"));
    private static Random geradorBoolean = new Random();

    public static UsuariosModel usuarioValido() {
        return novoUsuario();
    }

    public static UsuariosModel usuarioComNomeEmBranco() {
        UsuariosModel usuario = novoUsuario();
        usuario.setNome(StringUtils.EMPTY);

        return usuario;
    }

    public static UsuariosModel usuarioComEmailEmBranco() {
        UsuariosModel usuario = novoUsuario();
        usuario.setEmail(StringUtils.EMPTY);

        return usuario;
    }

    public static UsuariosModel usuarioComPasswordEmBranco() {
        UsuariosModel usuario = novoUsuario();
        usuario.setPassword(StringUtils.EMPTY);

        return usuario;
    }

    public static UsuariosModel usuarioComIsAdminEmBranco() {
        UsuariosModel usuario = novoUsuario();
        usuario.setAdministrador(StringUtils.EMPTY);

        return usuario;
    }

    public static UsuariosModel usuarioComEmailExistente() {
        UsuariosModel usuario = novoUsuario();

        String emailExistente =
                usuariosClient.getUsuarios()
                        .then()
                        .extract()
                        .path("usuarios[0].email");

        usuario.setEmail(emailExistente);

        return usuario;
    }

    public static String emailExistente() {
        return
            usuariosClient.getUsuarios()
                .then()
                    .extract()
                    .path("usuarios[0].email");


    }

    public static String nomeExistente() {
        return
                usuariosClient.getUsuarios()
                        .then()
                        .extract()
                        .path("usuarios[0].nome");


    }

    public static UsuarioResponseModel usuarioAdminLogin() {
        return
                usuariosClient.getUsuarios()
                        .then()
                        .extract()
                        .jsonPath()
                        .getObject("usuarios.find { it.administrador == 'true' }", UsuarioResponseModel.class);


    }

    public static String idExistente() {
        return
                usuariosClient.getUsuarios()
                        .then()
                        .extract()
                        .path("usuarios[0]._id");


    }

    public static UsuariosModel usuarioComDadosEmBranco() {

        UsuariosModel usuario = novoUsuario();
        usuario.setNome(StringUtils.EMPTY);
        usuario.setEmail(StringUtils.EMPTY);
        usuario.setPassword(StringUtils.EMPTY);
        usuario.setAdministrador(StringUtils.EMPTY);

        return usuario;
    }

    public static String criarUsuario(){
        UsuariosModel usuario = UsuariosDataFactory.usuarioValido();
        return
                usuariosClient.cadastrarUsuarios(usuario)
                .then()
                        .extract()
                        .path("_id");
    }

    private static UsuariosModel novoUsuario() {

        UsuariosModel usuario = new UsuariosModel();
        usuario.setNome(faker.name().firstName() + " " + faker.name().lastName());
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setPassword(faker.internet().password());
        usuario.setAdministrador(String.valueOf(geradorBoolean.nextBoolean()));

        return usuario;
    }
}
