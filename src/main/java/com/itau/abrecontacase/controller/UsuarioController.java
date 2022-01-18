package com.itau.abrecontacase.controller;


import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.itau.abrecontacase.entity.UsuarioEntity;
import com.itau.abrecontacase.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/usuario")
    public UsuarioEntity saveUsuario(@RequestBody UsuarioEntity usuarioEntity) {
        return usuarioRepository.save(usuarioEntity);
    }

    @GetMapping("/usuario/{cpf}")
    public PaginatedScanList<UsuarioEntity> getUsuario(@PathVariable("cpf") String cpf) {
        return usuarioRepository.getUsuarioByCpf(cpf);
    }

    @DeleteMapping("/usuario/{uid}")
    public UsuarioEntity deleteUsuario(@PathVariable("uid") String uid) {
        return usuarioRepository.delete(uid);
    }

    @PutMapping("/usuario/{uid}")
    public UsuarioEntity updateUsuario(@PathVariable("uid") String uid, @RequestBody UsuarioEntity usuarioEntity) {
        return usuarioRepository.update(uid, usuarioEntity);
    }
}
