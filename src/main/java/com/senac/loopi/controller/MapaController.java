package com.senac.loopi.controller;

import com.senac.loopi.model.rota.DadosSugestaoEndereco;
import com.senac.loopi.service.GeocodingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mapa")
@RequiredArgsConstructor
public class MapaController {

    private final GeocodingService geocodingService;

    // GET /api/mapa/sugestoes?busca=Afonso Pena
    @GetMapping("/sugestoes")
    public ResponseEntity<List<DadosSugestaoEndereco>> buscarEnderecos(@RequestParam String busca) {

        List<DadosSugestaoEndereco> resultados = geocodingService.buscarSugestoesDeEndereco(busca);

        return ResponseEntity.ok(resultados);
    }
}