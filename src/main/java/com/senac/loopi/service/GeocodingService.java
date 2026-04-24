package com.senac.loopi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.senac.loopi.model.rota.DadosRotaMapbox;
import com.senac.loopi.model.rota.DadosSugestaoEndereco;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeocodingService {

    @Value("${mapbox.token}")
    private String mapboxToken;

    private final String MAPBOX_URL = "https://api.mapbox.com/geocoding/v5/mapbox.places";
    private final String DIRECTIONS_URL = "https://api.mapbox.com/directions/v5/mapbox";

    // ========================================================================
    // MÉTODO 1: Transforma um endereço em texto para Coordenadas (Lat/Lon)
    // ========================================================================
    public float[] obterCoordenadas(String endereco) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            // A mágica do Spring: usando {variaveis} ele trata os espaços sozinho!
            String url = MAPBOX_URL + "/{endereco}.json?access_token={token}&limit=1";

            // Passamos as variáveis no final (endereco e mapboxToken) e ele encaixa na URL
            String response = restTemplate.getForObject(url, String.class, endereco, mapboxToken);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);

            JsonNode features = root.path("features");
            if (!features.isMissingNode() && features.isArray() && features.size() > 0) {
                JsonNode coordinates = features.get(0).path("center");
                return new float[]{
                        (float) coordinates.get(1).asDouble(), // Latitude
                        (float) coordinates.get(0).asDouble()  // Longitude
                };
            } else {
                System.err.println("Mapbox não encontrou esse lugar: " + endereco);
            }
        } catch (Exception e) {
            System.err.println("Erro na chamada do Mapbox (Coordenadas): " + e.getMessage());
        }
        return null;
    }

    // ========================================================================
    // MÉTODO 2: Pega o Tempo e a Distância entre dois pontos no mapa
    // ========================================================================
    public DadosRotaMapbox obterTempoEDistancia(double latOrigem, double lonOrigem, double latDestino, double lonDestino, String perfilTransporte) {
        RestTemplate restTemplate = new RestTemplate();
        // O Mapbox Directions EXIGE a ordem: Longitude,Latitude (Invertido!)
        String coordenadas = lonOrigem + "," + latOrigem + ";" + lonDestino + "," + latDestino;

        try {
            // Mesma estratégia aqui para a rota
            String url = DIRECTIONS_URL + "/{perfil}/{coordenadas}?access_token={token}";

            // Passamos as variáveis em ordem: perfilTransporte, coordenadas, mapboxToken
            String response = restTemplate.getForObject(url, String.class, perfilTransporte, coordenadas, mapboxToken);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);

            JsonNode route = root.path("routes").get(0);

            if (route != null && !route.isMissingNode()) {
                double distancia = route.path("distance").asDouble(); // Vem em metros
                double duracao = route.path("duration").asDouble();   // Vem em segundos

                return new DadosRotaMapbox(distancia, duracao);
            }
        } catch (Exception e) {
            System.err.println("Erro na chamada do Mapbox (Rotas): " + e.getMessage());
        }
        return null;
    }

    // ========================================================================
    // MÉTODO 3: Busca Sugestões de Endereço (Autocomplete) com Proximidade
    // ========================================================================
    public List<DadosSugestaoEndereco> buscarSugestoesDeEndereco(String busca) {
        List<DadosSugestaoEndereco> sugestoes = new ArrayList<>();

        if (busca == null || busca.trim().isEmpty()) {
            return sugestoes;
        }

        try {
            // A "Cerca" invisível ao redor da Região Metropolitana do Rio de Janeiro
            // Formato: minLongitude, minLatitude, maxLongitude, maxLatitude
            String bboxRio = "-43.80,-23.10,-43.00,-22.70";

            // Trocamos o proximity pelo bbox!
            String url = "https://api.mapbox.com/geocoding/v5/mapbox.places/{busca}.json?access_token={token}&autocomplete=true&country=br&bbox={caixa}&limit=2";

            RestTemplate restTemplate = new RestTemplate();

            // Passamos as variáveis na ordem exata: busca, token, bboxRio
            String response = restTemplate.getForObject(
                    url,
                    String.class,
                    busca,
                    mapboxToken,
                    bboxRio
            );

            if (response != null) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response);

                JsonNode features = root.path("features");

                for (JsonNode feature : features) {
                    String id = feature.path("id").asText();
                    String title = feature.path("text").asText(); // Nome principal

                    String subtitle = feature.path("place_name").asText().replace(title + ", ", "");

                    sugestoes.add(new DadosSugestaoEndereco(id, title, subtitle));
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar sugestões no Mapbox: " + e.getMessage());
        }

        return sugestoes;
    }
}