package br.com.vita.projeto.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vita.projeto.base.model.entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

    Pedido findByIdMongoDb(String id);
    
}
