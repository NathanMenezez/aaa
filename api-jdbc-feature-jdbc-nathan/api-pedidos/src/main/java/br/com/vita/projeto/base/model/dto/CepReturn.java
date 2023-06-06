package br.com.vita.projeto.base.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CepReturn {
    @JsonProperty("logradouro")
    private String logradouro;
}
