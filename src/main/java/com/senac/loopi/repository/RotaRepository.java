package com.senac.loopi.repository;

import com.senac.loopi.model.rota.Rota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RotaRepository extends JpaRepository<Rota, Integer> {
    List<Rota> findByStatus(int status);
}
