package br.com.vita.projeto.base.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.vita.projeto.base.model.dto.ProdutoReturn;

public class ProdutoRest {

    public static ProdutoReturn buscarProduto(Integer id){
        RestTemplate restTemplate = new RestTemplate();
        String produtoUrl = "http://localhost:8081/listar/" + id;
        ResponseEntity<ProdutoReturn> responseProduto = restTemplate.getForEntity(produtoUrl, ProdutoReturn.class);
        return responseProduto.getBody();
    }
}
