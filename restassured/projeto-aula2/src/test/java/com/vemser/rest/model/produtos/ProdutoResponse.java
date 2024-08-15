package com.vemser.rest.model.produtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProdutoResponse {

    private String nome;
    private int preco;
    private String descricao;
    private int quantidade;
    @JsonProperty("_id")
    private String id;

    public ProdutoResponse(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
