package br.com.vita.api.produtos.service;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.vita.api.produtos.dto.ProdutoDTO;
import br.com.vita.api.produtos.model.ProdutoModel;
import br.com.vita.api.produtos.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository repository;

    public ResponseEntity<String> cadastrarProduto(ProdutoDTO produtoDto) {
        if(repository.existsByNome(produtoDto.getNome())){
            return ResponseEntity.status(409).body("Produto já cadastrado no sistema!");
        }
        ProdutoModel produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(produtoDto, produtoModel);
        repository.save(produtoModel);
        
        return ResponseEntity.status(200).body("Produto cadastrado com sucesso!");
    }

    public ResponseEntity<?> listarProdutoNome(String nome) {
        if(!repository.existsByNome(nome)){
            return ResponseEntity.status(404).body("Produto não encontrado no sistema!");
        }
        return ResponseEntity.status(200).body(repository.findByNome(nome));
    }

    public ResponseEntity<String> alterarProduto(String nome, @Valid ProdutoDTO produtoDTO) {
        if(!repository.existsByNome(nome)){
            return ResponseEntity.status(404).body("Produto não encontrado no sistema!");
        }

        ProdutoModel produto = new ProdutoModel();
        BeanUtils.copyProperties(produtoDTO, produto);
        produto.setId(repository.findByNome(nome).getId());
        repository.update(produto);

        return ResponseEntity.status(200).body("Produto alterado com sucesso!");
    }

    public ResponseEntity<String> deletarProduto(String nome) {
        if(!repository.existsByNome(nome)){
            return ResponseEntity.status(404).body("Produto não encontrado no sistema!");
        }

        repository.deleteByNome(nome);

        return ResponseEntity.status(200).body("Produto deletado com sucesso!");
    }

    public ResponseEntity<?> listarProdutoId(Integer id) {
        if(!repository.existsById(id)){
            return ResponseEntity.status(404).body("Produto não encontrado no sistema!");
        }
        return ResponseEntity.status(200).body(repository.findById(id));
    }

    
}
