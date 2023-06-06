package br.com.vita.projeto.base.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.vita.projeto.base.model.entities.Pedido;
import br.com.vita.projeto.base.model.entities.PedidoMongo;
import br.com.vita.projeto.base.model.entities.PedidoMongoNotificacao;
import br.com.vita.projeto.base.model.enums.PedidoStatus;
import br.com.vita.projeto.base.repository.PedidoRepositoryMongo;
import br.com.vita.projeto.base.repository.PedidoRepositoryMongoNotificacao;

@Service
public class PedidoProcess {
    @Autowired
    PedidoRepositoryMongo pedidoRepositoryMongo;

    @Autowired
    PedidoRepositoryMongoNotificacao pedidoRepositoryMongoNotificacao;

    @Scheduled(fixedRate = 5 * 60 * 1000, initialDelay = 10000)
    public void processarPedido(){
        System.out.println("processando pedidos");
        List<PedidoMongo> pedidosPendente = pedidoRepositoryMongo.findByStatus("PENDENTE");

        for (PedidoMongo pedido : pedidosPendente) {
            PedidoMongoNotificacao pedidoNotificacao = new PedidoMongoNotificacao();
            BeanUtils.copyProperties(pedido, pedidoNotificacao);
            pedidoRepositoryMongoNotificacao.save(pedidoNotificacao);
            pedido.setStatus(PedidoStatus.PROCESSADO);
            pedidoRepositoryMongo.save(pedido);
        }
        System.out.println("pedidos processados");
    }

    @Scheduled(fixedRate = 10 * 60 * 1000, initialDelay = 40000)
    public void notificarPedido(){
        System.out.println("notificando pedido");
        RestTemplate template = new RestTemplate();

        List<PedidoMongoNotificacao> pedidosPendente = pedidoRepositoryMongoNotificacao.findByStatus("PENDENTE");

        for (PedidoMongoNotificacao pedidoPendente : pedidosPendente) {
            pedidoPendente.setStatus(PedidoStatus.NOTIFICADO);
            pedidoRepositoryMongoNotificacao.save(pedidoPendente);
            ResponseEntity<Pedido> responsePedido = template.getForEntity("http://localhost:8083/listar/mongoId/"+ pedidoPendente.getId(), Pedido.class);
            String url = "http://localhost:8083/atualizar/pedido/" + responsePedido.getBody().getId();
            ResponseEntity<String> response = template.exchange(url, HttpMethod.PUT, null ,String.class);
            System.out.println(response.getBody());
        }
        System.out.println("pedidos notificados");
    }

}
