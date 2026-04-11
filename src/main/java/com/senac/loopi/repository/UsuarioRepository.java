package com.senac.loopi.repository;

import com.senac.loopi.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario>findByStatus(int status);
}
