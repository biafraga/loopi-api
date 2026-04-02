package com.senac.loopi.service;

import com.senac.loopi.model.usuario.Usuario;
import com.senac.loopi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    //Criar ou alterar usuário
    public Usuario salvarUsuario(Usuario usuario){
        // Antes do save, faremos: usuario.setSenha(senhaCriptografada);
        return usuarioRepository.save(usuario);
    }

    //Listar usuários
    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    //Procurar usuário pelo Id
    public Usuario obterUsuarioPeloId(Integer id){
        return usuarioRepository.findById(id).orElse(null);
    }

    //Deletar usuário
    public void deletarUsuario(Integer id){
        Usuario usuario = obterUsuarioPeloId(id);
        usuario.setStatus(0);
        usuarioRepository.save(usuario);
    }
}
