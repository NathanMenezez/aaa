package br.com.vita.projeto.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import br.com.vita.projeto.base.model.dto.ClienteReturn;
import br.com.vita.projeto.base.model.dto.MensagemRetorno;
import br.com.vita.projeto.base.model.dto.PedidoDTO;
import br.com.vita.projeto.base.model.dto.ProdutoReturn;
import br.com.vita.projeto.base.model.entities.Pedido;
import br.com.vita.projeto.base.model.entities.PedidoMongo;
import br.com.vita.projeto.base.model.enums.PedidoStatus;
import br.com.vita.projeto.base.repository.PedidoRepository;
import br.com.vita.projeto.base.repository.PedidoRepositoryMongo;
@Service
public class PedidoService {

    @Autowired
    PedidoRepository repository;

    @Autowired
    PedidoRepositoryMongo repositoryMongo;

    public ResponseEntity<MensagemRetorno> criarPedido(PedidoDTO pedidoDTO) {
        
        ClienteReturn cliente = null;
        try {
            cliente = ClienteRest.consultarCpf(pedidoDTO.getCpf());
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(404).body(new MensagemRetorno("CPF informado invalido!!"));
        }

        String ruaEntrega = "";
        try {
            ruaEntrega = EnderecoRest.buscarEndereco(pedidoDTO.getCepEntrega());
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(404).body(new MensagemRetorno("CEP Invalido!"));
        }

        ProdutoReturn produto = null;
        try {
            produto = ProdutoRest.buscarProduto(pedidoDTO.getIdProduto());
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(404).body(new MensagemRetorno("ID Produto invalido!!"));
        }
        if(produto != null){
            if(produto.getQuantidadeEstoque() < pedidoDTO.getQuantidade()){
                return ResponseEntity.status(409).body(new MensagemRetorno("Quantidade de produto insuficiente!!"));
            }
        }   

        Pedido pedido = new Pedido(produto.getId(), cliente.getId(), ruaEntrega, produto.getPreco() * pedidoDTO.getQuantidade());
        

        PedidoMongo pedidoMongo = new PedidoMongo(produto.getId(), cliente.getId(), ruaEntrega, produto.getPreco() * pedidoDTO.getQuantidade());
        String idMongo = repositoryMongo.save(pedidoMongo).getId();
        pedido.setIdMongoDb(idMongo);
        repository.save(pedido);

        return ResponseEntity.status(200).body(new MensagemRetorno("Pedido Gerado com Sucesso!"));
    }

    public ResponseEntity<?> buscarPedido(Integer id) {
        if(!repository.existsById(id)){
            return ResponseEntity.status(404).body(new MensagemRetorno("Pedido não encontrado no sistema!"));
        }
        return ResponseEntity.status(200).body(repository.findById(id));
    }

    public ResponseEntity<MensagemRetorno> processarPedido(Integer id) {
        if(!repository.existsById(id)){
            return ResponseEntity.status(404).body(new MensagemRetorno("Pedido não encontrado no sistema!"));
        }
        Pedido pedido = repository.findById(id).get();
        if(pedido.getStatus() == PedidoStatus.PROCESSADO){
            return ResponseEntity.status(409).body(new MensagemRetorno("Pedido já processado!"));
        }
        pedido.setStatus(PedidoStatus.PROCESSADO);
        repository.save(pedido);
        return ResponseEntity.status(200).body(new MensagemRetorno("Pedido processado com sucesso com sucesso!"));
    }

    public ResponseEntity<List<PedidoMongo>> listarStatus() {
        return ResponseEntity.status(200).body(repositoryMongo.findByStatus("PENDENTE"));
    }

    public ResponseEntity<?> buscarPedidoMongo(String id) {
        if(!repositoryMongo.existsById(id)){
            return ResponseEntity.status(404).body("Pedido não encontrado no sistema!");
        }
        return ResponseEntity.status(200).body(repository.findByIdMongoDb(id));
    }
    
}
