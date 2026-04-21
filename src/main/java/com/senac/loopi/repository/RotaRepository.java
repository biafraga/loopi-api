package com.senac.loopi.repository;

import com.senac.loopi.model.rota.Rota;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RotaRepository extends JpaRepository<Rota, Integer> {
    Page<Rota> findByStatus(int status, Pageable pageable);
}
