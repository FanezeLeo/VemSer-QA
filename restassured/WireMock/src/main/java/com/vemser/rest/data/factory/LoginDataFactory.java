package com.vemser.rest.data.factory;

import com.vemser.rest.client.LoginClient;
import com.vemser.rest.model.produtos.ProdutosModel;

public class LoginDataFactory {
    private static final LoginClient loginClient = new LoginClient();

    public static String auth(String email, String password) {
        return
                loginClient.login(email, password)
                    .then()
                        .extract()
                        .path("authorization");


    }
}
