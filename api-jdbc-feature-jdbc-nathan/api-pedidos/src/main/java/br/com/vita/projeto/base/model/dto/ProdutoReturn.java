package br.com.vita.projeto.base.model.dto;

import lombok.Data;

@Data
public class ProdutoReturn {
    private Integer id;
    private String nome;
    private String descricao;
    private Double preco;
    private Integer quantidadeEstoque;
}
