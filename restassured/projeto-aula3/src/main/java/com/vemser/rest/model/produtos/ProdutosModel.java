package com.vemser.rest.model.produtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutosModel {
    private String nome;
    private int preco;
    private String descricao;
    private int quantidade;
}
