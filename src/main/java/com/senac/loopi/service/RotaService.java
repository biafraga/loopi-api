package com.senac.loopi.service;

import com.senac.loopi.entity.Rota;
import com.senac.loopi.repository.RotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RotaService {
    private final RotaRepository rotaRepository;

    public RotaService (RotaRepository rotaRepository){
        this.rotaRepository = rotaRepository;
    }

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

    //Deletar rota
    public void deletarRota(int id){
        rotaRepository.deleteById(id);
    }
}
