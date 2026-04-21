package com.senac.loopi.service;

import com.senac.loopi.model.usuario.Usuario;
import com.senac.loopi.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    //Criar ou alterar usuário
    @Transactional
    public Usuario salvarUsuario(Usuario usuario){
        // Antes do save, faremos: usuario.setSenha(senhaCriptografada);
        return usuarioRepository.save(usuario);
    }

    //Listar usuários
    public Page<Usuario> listarUsuarios(Pageable pageable){
        return usuarioRepository.findByStatus(1, pageable);
    }

    //Procurar usuário pelo Id
    public Usuario obterUsuarioPeloId(Integer id){
        return usuarioRepository.findById(id).orElse(null);
    }

    //Deletar usuário
    @Transactional
    public void deletarUsuario(Integer id){
        Usuario usuario = obterUsuarioPeloId(id);
        if (usuario != null){
            usuario.setStatus(0);
            usuarioRepository.save(usuario);
        }
    }
}
