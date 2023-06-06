package br.com.vita.projeto.base.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.vita.projeto.base.model.enums.MetodosPagamento;
import lombok.Data;

@Data
public class PedidoDTO {
     @NotBlank(message = "CPF não pode estar vazio!")
     private String cpf;

     @NotNull(message = "Preencha o idProduto")
     private Integer idProduto;

     @NotNull(message = "Preencha a quantidade!")
     private Integer quantidade;

     @NotNull(message = "Preencha a forma de pagamento!")
     private MetodosPagamento formaDePagamento;

     @NotBlank(message = "Preencha o CEP")
     private String cepEntrega;
}
