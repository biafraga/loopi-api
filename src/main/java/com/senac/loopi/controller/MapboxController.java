package com.senac.loopi.controller;

import com.senac.loopi.model.rota.DadosRotaMapbox;
import com.senac.loopi.service.GeocodingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mapas")
@RequiredArgsConstructor
public class MapboxController {
    private final GeocodingService geocodingService;

    // GET /api/mapas/teste-rota?origem=Maracana&destino=Copacabana&transporte=driving
    @GetMapping("/teste-rota")
    public DadosRotaMapbox testarRota(
            @RequestParam String origem,
            @RequestParam String destino,
            @RequestParam String transporte) {

        // 1. Transforma os textos dos endereços em Coordenadas (Lat/Lon)
        float[] coordOrigem = geocodingService.obterCoordenadas(origem);
        float[] coordDestino = geocodingService.obterCoordenadas(destino);

        if (coordOrigem == null || coordDestino == null) {
            throw new RuntimeException("Não foi possível encontrar as coordenadas desses endereços.");
        }

        // 2. Manda as coordenadas para o Mapbox calcular o tempo e a distância
        return geocodingService.obterTempoEDistancia(
                coordOrigem[0], coordOrigem[1],
                coordDestino[0], coordDestino[1],
                transporte
        );
    }
}

