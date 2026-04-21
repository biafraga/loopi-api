package com.senac.loopi.service;

import com.senac.loopi.model.alerta.Alerta;
import com.senac.loopi.repository.AlertaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AlertaService {
    private final AlertaRepository alertaRepository;

    //Criar ou alterar alerta
    @Transactional
    public Alerta salvarAlerta(Alerta alerta){
        return alertaRepository.save(alerta);
    }

    // Listar alertas
    public Page<Alerta> listarAlertas(Pageable pageable){
        return alertaRepository.findByStatus(1, pageable);
    }

    //Procurar alerta por Id
    public Alerta obterAlertaPeloId(Integer id){
        return alertaRepository.findById(id).orElse(null);
    }

    //Soft delete
    @Transactional
    public void deletarAlerta(Integer id){
        Alerta alerta = obterAlertaPeloId(id);
        if (alerta != null) {
            alerta.setStatus(0); // inativo
            alertaRepository.save(alerta);
        }
    }

    //Calcula a hora de sair de casa baseado no Mapbox
    public LocalDateTime calcularHoraDaNotificacao(Alerta alerta, double segundosMapbox){
        // Transforma os segundos do Mapbox em um formato de tempo do Java
        Duration tempoDeViagem = Duration.ofSeconds((long) segundosMapbox);

        // Pega os minutos de antecedência que o usuário cadastrou no alerta
        Duration tempoParaSeArrumar = Duration.ofMinutes(alerta.getAntecedenciaMinutos());

        // Hora de chegada - tempo de viagem - tempo de se arrumar
        return alerta.getHorarioChegada()
                .minus(tempoDeViagem)
                .minus(tempoParaSeArrumar);
    }

}
