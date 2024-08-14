package com.vemser.rest.model.login;

public class LoginPOJO {
    private String email;
    private String password;

    public LoginPOJO(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
