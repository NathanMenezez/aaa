package br.com.vita.api.usuarios.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.vita.api.usuarios.dto.UpdateUsuario;
import br.com.vita.api.usuarios.dto.UsuarioDTO;
import br.com.vita.api.usuarios.service.UsuarioService;

@RestController
public class UsuarioController {
    
    @Autowired
    UsuarioService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO){
        return service.cadastrarUsuario(usuarioDTO);
    }

    @GetMapping("/listar/{cpf}")
    public ResponseEntity<?> listarUsuarioCpf(@PathVariable String cpf){
        return service.listarUsuarioCpf(cpf);
    }

    @DeleteMapping("/deletar/{cpf}")
    public ResponseEntity<String> deletarUsuario(@PathVariable String cpf){
        return service.deletarUsuario(cpf);
    }

    @PutMapping("/alterar/{cpf}")
    public ResponseEntity<String> alterarUsuario(@PathVariable String cpf, @RequestBody UpdateUsuario usuarioDTO){
        return service.alterarUsuario(cpf, usuarioDTO);
    }
}
