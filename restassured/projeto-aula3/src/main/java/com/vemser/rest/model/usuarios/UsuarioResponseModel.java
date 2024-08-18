package com.vemser.rest.model.usuarios;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseModel {
    private String nome;
    private String email;
    private String password;
    private String administrador;
    @JsonProperty("_id")
    private String id;
}
