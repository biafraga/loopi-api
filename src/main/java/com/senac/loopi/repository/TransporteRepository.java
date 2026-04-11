package com.senac.loopi.repository;

import com.senac.loopi.model.transporte.Transporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransporteRepository extends JpaRepository<Transporte, Integer> {
    List<Transporte> findByRotaId(Integer rotaId);

    List<Transporte> findByStatus(int status);
}
