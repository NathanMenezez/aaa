package br.com.vita.projeto.base.model.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.vita.projeto.base.model.enums.PedidoStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer idProduto;

    private Integer idUsuario;

    private String ruaDeEntrega;

    private LocalDateTime dataPedido = LocalDateTime.now();

    private LocalDateTime dataConclusao;
    
    @Enumerated(EnumType.STRING)
    private PedidoStatus status = PedidoStatus.PENDENTE;

    private Double valorTotal;

    public Pedido(Integer idProduto, Integer idUsuario, String ruaDeEntrega, Double valorTotal) {
        this.idProduto = idProduto;
        this.idUsuario = idUsuario;
        this.ruaDeEntrega = ruaDeEntrega;
        this.valorTotal = valorTotal;
    }

}
