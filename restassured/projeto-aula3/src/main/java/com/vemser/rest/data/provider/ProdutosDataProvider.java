package com.vemser.rest.data.provider;

import com.vemser.rest.data.factory.ProdutosDataFactory;
import com.vemser.rest.data.factory.UsuariosDataFactory;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class ProdutosDataProvider {
    private static final String KEY_NOME = "nome";
    private static final String VALUE_NOME_EM_BRANCO = "nome não pode ficar em branco";
    private static final String KEY_PRECO = "preco";
    private static final String VALUE_PRECO_EM_BRANCO = "preco deve ser um número";
    private static final String KEY_DESCRICAO = "descricao";
    private static final String VALUE_DESCRICAO_EM_BRANCO = "descricao não pode ficar em branco";
    private static final String KEY_QUANTIDADE = "quantidade";
    private static final String VALUE_QUANTIDADE_EM_BRANCO = "quantidade deve ser um número";

    public static Stream<Arguments> produtosDataProvider() {
        return Stream.of(
                Arguments.of(ProdutosDataFactory.produtoComNomeEmBranco(), KEY_NOME, VALUE_NOME_EM_BRANCO),
                Arguments.of(ProdutosDataFactory.produtoComPrecoEmBranco(), KEY_PRECO, VALUE_PRECO_EM_BRANCO),
                Arguments.of(ProdutosDataFactory.produtoComDescricaoEmBranco(), KEY_DESCRICAO, VALUE_DESCRICAO_EM_BRANCO),
                Arguments.of(ProdutosDataFactory.produtoComQuantidadeEmBranco(), KEY_QUANTIDADE, VALUE_QUANTIDADE_EM_BRANCO)
        );
    }
}
