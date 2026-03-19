package com.senac.loopi.service;

import com.senac.loopi.entity.Alerta;
import com.senac.loopi.repository.AlertaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertaService {
    private final AlertaRepository alertaRepository;

    public AlertaService (AlertaRepository alertaRepository){
        this.alertaRepository = alertaRepository;
    }

    //Criar ou alterar alerta
    public Alerta salvarAlerta(Alerta alerta){
        return alertaRepository.save(alerta);
    }

    // Listar alertas
    public List<Alerta> listarAlertas(){
        return alertaRepository.findAll();
    }

    //Procurar alerta por Id
    public Alerta obterAlertaPeloId(Integer id){
        return alertaRepository.findById(id).orElse(null);
    }

    //Deletar alerta
    public void deletarAlerta(int id){
        alertaRepository.deleteById(id);
    }
}
