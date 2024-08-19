package com.vemser.rest.data.factory;

import com.vemser.rest.client.ProdutoClient;
import com.vemser.rest.model.produtos.ProdutosModel;
import net.datafaker.Faker;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.Random;

public class ProdutosDataFactory {
    private static final ProdutoClient produtosClient = new ProdutoClient();
    private static final Faker faker = new Faker(new Locale("PT-BR"));
    private static final Random geradorQuantidade = new Random();

    public static ProdutosModel produtoValido() {
        return novoProduto();
    }

    public static ProdutosModel produtoComNomeEmBranco() {
        ProdutosModel produto = novoProduto();
        produto.setNome(StringUtils.EMPTY);

        return produto;
    }

    public static ProdutosModel produtoComPrecoEmBranco() {
        ProdutosModel produto = novoProduto();
        produto.setPreco(0);

        return produto;
    }

    public static ProdutosModel produtoComDescricaoEmBranco() {
        ProdutosModel produto = novoProduto();
        produto.setDescricao(StringUtils.EMPTY);

        return produto;
    }

    public static ProdutosModel produtoComQuantidadeEmBranco() {
        ProdutosModel produto = novoProduto();
        produto.setQuantidade(0);

        return produto;
    }

    public static ProdutosModel produtoComNomeExistente() {
        ProdutosModel produto = novoProduto();

        String nomeExistente =
                produtosClient.getProdutos()
                        .then()
                        .extract()
                        .path("produtos[0].nome");

        produto.setNome(nomeExistente);

        return produto;
    }

    public static String nomeExistente() {
        return
                produtosClient.getProdutos()
                        .then()
                        .extract()
                        .path("produtos[0].nome");
    }

    public static String idExistente() {
        return
                produtosClient.getProdutos()
                        .then()
                        .extract()
                        .path("produtos[0]._id");
    }

    public static String descricaoExistente() {
        return
                produtosClient.getProdutos()
                        .then()
                        .extract()
                        .path("produtos[0].descricao");
    }

    public static int quantidadeExistente() {
        return
                produtosClient.getProdutos()
                        .then()
                        .extract()
                        .path("produtos[0].quantidade");
    }

    public static int precoExistente() {
        return
                produtosClient.getProdutos()
                        .then()
                        .extract()
                        .path("produtos[0].preco");
    }

    public static ProdutosModel produtoComDadosEmBranco() {

        ProdutosModel produto = novoProduto();
        produto.setNome(StringUtils.EMPTY);
        produto.setPreco(0);
        produto.setDescricao(StringUtils.EMPTY);
        produto.setQuantidade(0);

        return produto;
    }

    public static String criarProduto() {
        ProdutosModel produto = ProdutosDataFactory.produtoValido();
        return
                produtosClient.cadastrarProdutos(produto)
                        .then()
                        .extract()
                        .path("_id");
    }

    private static ProdutosModel novoProduto() {

        ProdutosModel produto = new ProdutosModel();
        produto.setNome(faker.commerce().productName());
        produto.setPreco(faker.number().randomDigitNotZero());
        produto.setDescricao(faker.commerce().material());
        produto.setQuantidade(geradorQuantidade.nextInt(100) + 1);

        return produto;
    }

}
