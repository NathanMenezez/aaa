package br.com.vita.projeto.base.model.entities;

import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.vita.projeto.base.model.enums.PedidoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "notificacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoMongoNotificacao {

    @Id
    private String id;

    private Integer idProduto;

    private Integer idUsuario;

    private String ruaDeEntrega;

    private LocalDateTime dataPedido = LocalDateTime.now();

    private LocalDateTime dataConclusao = null;
    
    @Enumerated(EnumType.STRING)
    private PedidoStatus status = PedidoStatus.PENDENTE;

    private Double valorTotal;

    public PedidoMongoNotificacao(Integer idProduto, Integer idUsuario, String ruaDeEntrega, Double valorTotal) {
        this.idProduto = idProduto;
        this.idUsuario = idUsuario;
        this.ruaDeEntrega = ruaDeEntrega;
        this.valorTotal = valorTotal;
    }

}