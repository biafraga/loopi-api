package com.senac.loopi.service;

import com.senac.loopi.model.rota.Rota;
import com.senac.loopi.repository.RotaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RotaService {
    private final RotaRepository rotaRepository;

    //Criar ou alterar rota
    @Transactional
    public Rota salvarRota(Rota rota){
        return rotaRepository.save(rota);
    }

    //Listar rotas
    public Page<Rota> listarRotas(Pageable pageable){
        return rotaRepository.findByStatus(1, pageable);
    }

    // Procurar rotas pelo Id
    public Rota obterRotaPeloId(Integer id){
        return rotaRepository.findById(id).orElse(null);
    }

    //Soft delete
    @Transactional
    public void deletarRota(Integer id){
        Rota rota = obterRotaPeloId(id);
        if (rota != null){
            rota.setStatus(0); //inativo
            rotaRepository.save(rota);
        }
    }
}
