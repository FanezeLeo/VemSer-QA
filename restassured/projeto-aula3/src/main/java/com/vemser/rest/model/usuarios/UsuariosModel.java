package com.vemser.rest.model.usuarios;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuariosModel {
    private String nome;
    private String email;
    private String password;
    private String administrador;
}
