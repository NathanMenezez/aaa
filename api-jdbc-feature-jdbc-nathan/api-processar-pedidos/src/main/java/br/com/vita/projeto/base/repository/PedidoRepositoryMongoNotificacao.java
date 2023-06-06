package br.com.vita.projeto.base.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.vita.projeto.base.model.entities.PedidoMongoNotificacao;

@Repository
public interface PedidoRepositoryMongoNotificacao extends MongoRepository<PedidoMongoNotificacao, String>{

    List<PedidoMongoNotificacao> findByStatus(String string);
}
