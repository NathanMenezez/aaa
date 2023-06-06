package br.com.vita.api.usuarios.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UpdateUsuario {
    @NotBlank(message = "Preencha o nome!")
    private String nome;

    @NotBlank(message = "Preencha o email!")
    @Email
    private String email;

    @NotBlank(message = "Preencha o telefone!")
    private String telefone;

    @NotBlank(message = "Preencha o endereco!")
    private String endereco;
}
