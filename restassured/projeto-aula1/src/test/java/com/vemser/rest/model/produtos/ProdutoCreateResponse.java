package com.vemser.rest.model.produtos;

public class ProdutoCreateResponse {
    private String message;
    private String _id;

    public ProdutoCreateResponse(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
