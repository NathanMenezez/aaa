package br.com.vita.projeto.base.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.vita.projeto.base.model.dto.ClienteReturn;

public class ClienteRest {
    
    public static ClienteReturn consultarCpf(String cpf){
        RestTemplate restTemplate = new RestTemplate();
        String clienteUrl = "http://localhost:8080/listar/" + cpf;
        ResponseEntity<ClienteReturn> responseCliente = restTemplate.getForEntity(clienteUrl,ClienteReturn.class);
        return responseCliente.getBody();
    }
}