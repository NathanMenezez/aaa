package br.com.vita.api.usuarios.service;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.vita.api.usuarios.dto.UpdateUsuario;
import br.com.vita.api.usuarios.dto.UsuarioDTO;
import br.com.vita.api.usuarios.model.UsuarioModel;
import br.com.vita.api.usuarios.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;


    public ResponseEntity<String> cadastrarUsuario(@Valid UsuarioDTO usuarioDTO) {
        if(repository.existsByCPF(usuarioDTO.getCpf())){
            return ResponseEntity.status(409).body("CPF já cadastrado no sistema!");
        }

        UsuarioModel usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDTO, usuarioModel);
        repository.save(usuarioModel);
        return ResponseEntity.status(200).body("Usuario cadastrado com sucesso!");
    }


    public ResponseEntity<?> listarUsuarioCpf(String cpf) {
        if(!repository.existsByCPF(cpf)){
            return ResponseEntity.status(404).body("Usuario não encontrado no sistema!");
        }

        return ResponseEntity.status(200).body(repository.findByCpf(cpf));
    }


    public ResponseEntity<String> deletarUsuario(String cpf) {
        if(!repository.existsByCPF(cpf)){
            return ResponseEntity.status(404).body("Usuario não encontrado no sistema!");
        }

        repository.deleteByCpf(cpf);
        return ResponseEntity.status(200).body("Usuario deletado com sucesso!");
    }


    public ResponseEntity<String> alterarUsuario(String cpf, UpdateUsuario usuarioDTO) {
        if(!repository.existsByCPF(cpf)){
            return ResponseEntity.status(404).body("Usuario não encontrado no sistema!");
        }
        
        UsuarioModel usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDTO, usuarioModel);
        usuarioModel.setId(repository.findByCpf(cpf).getId());
        usuarioModel.setCpf(repository.findByCpf(cpf).getCpf());
        repository.update(usuarioModel);
        return ResponseEntity.status(200).body("Usuario alterado com sucesso!");
    }
    
}
