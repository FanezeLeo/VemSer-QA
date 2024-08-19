package com.vemser.rest.model.produtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutosResponseModel {
    private String nome;
    private int preco;
    private String descricao;
    private int quantidade;
    @JsonProperty("_id")
    private String id;
}
