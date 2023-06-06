package br.com.vita.projeto.base.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClienteReturn {
    @JsonProperty("id")
    private Integer id;
    
    @JsonProperty("nome")
    private String nome;
    
    @JsonProperty("cpf")
    private String cpf;
    
    @JsonProperty("email")
    private String email;

    @JsonProperty("telefone")
    private String telefone;

    @JsonProperty("endereco")
    private String endereco;
}
