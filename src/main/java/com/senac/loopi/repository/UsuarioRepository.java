package com.senac.loopi.repository;

import com.senac.loopi.model.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Page<Usuario> findByStatus(int staus, Pageable pageable);
}
