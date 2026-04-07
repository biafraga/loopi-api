package com.senac.loopi.service;

import com.senac.loopi.model.alerta.Alerta;
import com.senac.loopi.repository.AlertaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertaService {
    private final AlertaRepository alertaRepository;

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

    //Soft delete
    public void deletarAlerta(Integer id){
        Alerta alerta = obterAlertaPeloId(id);
        if (alerta != null) {
            alerta.setStatus(0); // inativo
            alertaRepository.save(alerta);
        }
    }
}
