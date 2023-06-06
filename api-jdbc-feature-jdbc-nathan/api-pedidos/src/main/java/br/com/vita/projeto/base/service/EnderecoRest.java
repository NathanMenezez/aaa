package br.com.vita.projeto.base.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.vita.projeto.base.model.dto.CepReturn;

public class EnderecoRest {
    public static String buscarEndereco(String cep){
        RestTemplate restTemplate = new RestTemplate();
        String entregaUrl = "https://viacep.com.br/ws/" + cep + "/json/";
        ResponseEntity<CepReturn> responseCep = restTemplate.getForEntity(entregaUrl, CepReturn.class);
        return responseCep.getBody().getLogradouro();
    }
}
