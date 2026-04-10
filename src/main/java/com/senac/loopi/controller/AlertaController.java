package com.senac.loopi.controller;

import com.senac.loopi.model.alerta.Alerta;
import com.senac.loopi.model.alerta.DadosAtualizacaoAlerta;
import com.senac.loopi.model.alerta.DadosCadastroAlerta;
import com.senac.loopi.model.alerta.DadosDetalhamentoAlerta;
import com.senac.loopi.model.rota.DadosRotaMapbox;
import com.senac.loopi.model.rota.Rota;
import com.senac.loopi.service.AlertaService;
import com.senac.loopi.service.GeocodingService;
import com.senac.loopi.service.RotaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/alertas")
@RequiredArgsConstructor
public class AlertaController {
    private final AlertaService alertaService;
    private final RotaService rotaService; // Precisamos para achar a rota do alerta
    private final GeocodingService geocodingService;

    // GET /api/alertas
    @GetMapping
    public List<DadosDetalhamentoAlerta> listarAlertas(){
        return alertaService.listarAlertas().stream()
                .map(DadosDetalhamentoAlerta::new)
                .toList();
    }

    // GET /api/alertas/1
    @GetMapping("/{id}")
    public DadosDetalhamentoAlerta obterAlertaPeloId(@PathVariable Integer id){
        Alerta alerta = alertaService.obterAlertaPeloId(id);
        return new DadosDetalhamentoAlerta(alerta);
    }

    //POST /api/alertas
    @PostMapping
    public DadosDetalhamentoAlerta adicionarAlerta(@RequestBody @Valid DadosCadastroAlerta dados){
        // Busca a rota no banco de dados
        Rota rota = rotaService.obterRotaPeloId(dados.rotaId());
        if (rota == null) {
            throw new RuntimeException("Rota não encontrada!");
        }

        // Pergunta pro Mapbox o tempo da viagem (Temporariamente como "driving")
        DadosRotaMapbox mapboxInfo = geocodingService.obterTempoEDistancia(
                rota.getLatitudeOrigem(), rota.getLongitudeOrigem(),
                rota.getLatitudeDestino(), rota.getLongitudeDestino(),
                "driving"
        );

        // Monta o Alerta base
        Alerta novoAlerta = new Alerta();
        novoAlerta.setHorarioChegada(dados.horarioChegada());
        novoAlerta.setDiasSemana(dados.diasSemana());
        novoAlerta.setAntecedenciaMinutos(dados.antecedenciaMinutos());
        novoAlerta.setStatus(1);
        novoAlerta.setRota(rota);

        // Calcula a hora exata do despertador e salva no objeto!
        if (mapboxInfo != null) {
            LocalDateTime horaCalculada = alertaService.calcularHoraDaNotificacao(novoAlerta, mapboxInfo.duracaoSegundos());
            novoAlerta.setHorarioNotificacao(horaCalculada);
        }

        Alerta alertaSalvo = alertaService.salvarAlerta(novoAlerta);
        return new DadosDetalhamentoAlerta(alertaSalvo);
    }

    // PUT /api/alertas/1
    @PutMapping("/{id}")
    public DadosDetalhamentoAlerta atualizarAlerta(@PathVariable Integer id, @RequestBody @Valid DadosAtualizacaoAlerta dados){
        Alerta alertaExistente = alertaService.obterAlertaPeloId(id);

        alertaExistente.setHorarioChegada(dados.horarioChegada());
        alertaExistente.setDiasSemana(dados.diasSemana());
        alertaExistente.setAntecedenciaMinutos(dados.antecedenciaMinutos());

        Alerta alertaAtualizado = alertaService.salvarAlerta(alertaExistente);
        return new DadosDetalhamentoAlerta(alertaAtualizado);
    }

    // DELETE /api/alertas/1
    @DeleteMapping("/{id}")
    public void deletarAlerta(@PathVariable Integer id){
        alertaService.deletarAlerta(id);
    }
}
