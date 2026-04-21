package com.senac.loopi.repository;

import com.senac.loopi.model.alerta.Alerta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertaRepository extends JpaRepository<Alerta, Integer> {
    Page<Alerta>findByStatus(int status, Pageable pageable);
}
