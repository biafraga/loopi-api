package com.senac.loopi.service;

import com.senac.loopi.model.transporte.Transporte;
import com.senac.loopi.repository.TransporteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransporteService {
    private final TransporteRepository transporteRepository;

    //criar ou alterar transporte
    public Transporte salvarTransporte(Transporte transporte){
        return transporteRepository.save(transporte);
    }

    // listar transportes
    public List<Transporte> listarTransportes(){
        return transporteRepository.findByStatus(1);
    }

    //procurar transporte pelo id
    public Transporte obterTransportePeloId(Integer id){
        return transporteRepository.findById(id).orElse(null);
    }

    //soft delete
    public void deletarTransporte(Integer id){
        Transporte transporte = obterTransportePeloId(id);
        if (transporte != null) {
            transporte.setStatus(0); // inativo
            transporteRepository.save(transporte);
        }
    }

    // Busca qual transporte está amarrado a essa rota
    public Transporte obterTransportePelaRotaId(Integer rotaId) {
        // Usa o método que você vai criar no Repository no próximo passo
        return transporteRepository.findByRotaId(rotaId).stream().findFirst().orElse(null);
    }

    // O Tradutor Inteligente
    public String traduzirTipoParaMapbox(String tipoBrasileiro) {
        if (tipoBrasileiro == null) return "driving"; // Prevenção de erro

        String tipo = tipoBrasileiro.toLowerCase().trim();

        if (tipo.contains("pé") || tipo.contains("andando") || tipo.contains("caminhada")) {
            return "walking";
        }
        else if (tipo.contains("bicicleta") || tipo.contains("bike")) {
            return "cycling";
        }
        else {
            // Aqui caem: Carro, Uber, Ônibus, Van, Metrô, Trem, Barca...
            // LIMITAÇÃO DO MVP: O Mapbox só entende rotas de asfalto (driving).
            // Estamos usando isso como uma aproximação de tempo para que a
            // matemática do Alerta funcione, cientes de que trilhos e mar têm tempos diferentes.
            return "driving";
        }
    }
}
