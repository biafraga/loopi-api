package com.senac.loopi.service;

import com.senac.loopi.model.rota.Rota;
import com.senac.loopi.repository.RotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RotaService {
    private final RotaRepository rotaRepository;

    //Criar ou alterar rota
    public Rota salvarRota(Rota rota){
        return rotaRepository.save(rota);
    }

    //Listar rotas
    public List<Rota> listarRotas(){
        return rotaRepository.findAll();
    }

    // Procurar rotas pelo Id
    public Rota obterRotaPeloId(Integer id){
        return rotaRepository.findById(id).orElse(null);
    }

    //Soft delete
    public void deletarRota(Integer id){
        Rota rota = obterRotaPeloId(id);
        if (rota != null){
            rota.setStatus(0); //inativo
            rotaRepository.save(rota);
        }
    }
}
