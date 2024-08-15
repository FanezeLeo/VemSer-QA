package com.vemser.rest.model.produtos;

public class ProdutoListarProdutos {

    private int quantidade;
    private ProdutoResponse[] produtos;

    public ProdutoListarProdutos(){

    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public ProdutoResponse[] getProdutos() {
        return produtos;
    }

    public void setProdutos(ProdutoResponse[] produtos) {
        this.produtos = produtos;
    }
}
