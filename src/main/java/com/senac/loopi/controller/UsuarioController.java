package com.senac.loopi.controller;

import com.senac.loopi.entity.Usuario;
import com.senac.loopi.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    // GET /api/usuarios
    @GetMapping
    public List<Usuario> listarUsuarios(){
        return usuarioService.listarUsuarios();
    }

    // GET /api/usuarios/1
    @GetMapping("/{id}")
    public Usuario obterUsuarioPeloId(@PathVariable Integer id){
        return usuarioService.obterUsuarioPeloId(id);
    }

    //POST /api/usuarios
    @PostMapping
    public Usuario adicionarUsuario(@RequestBody Usuario usuario){
        return usuarioService.salvarUsuario(usuario);
    }

    // PUT /api/usuarios/1
    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario){
        // vai garantir que o ID da URL seja setado no objeto antes de salvar
        usuario.setId(id);
        return usuarioService.salvarUsuario(usuario);
    }

    // DELETE /api/usuarios/1
    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Integer id){
        usuarioService.deletarUsuario(id);
    }
}
