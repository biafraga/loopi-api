package com.senac.loopi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
public class GeocodingService {
    @Value("${mapbox.token}")
    private String mapboxToken;

    private final String MAPBOX_URL= "https://api.mapbox.com/geocoding/v5/mapbox.places/";

    public float[] obterCoordenadas(String endereco){
        RestTemplate restTemplate = new RestTemplate();
        // O MapBox precisa do endereço na URL. O "types=address" foca em endereços reais.
        String url = String.format("%s%s.json?access_token=%s&types=address&limit=1", MAPBOX_URL, endereco, mapboxToken);

        try{
            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper mapper= new ObjectMapper();
            JsonNode root= mapper.readTree(response);

            // O MapBox devolve as coordenadas dentro de "features[0].center"
            // Formato: [longitude, latitude]
            JsonNode coordinates = root.path("features").get(0).path("center");

            if (coordinates != null && coordinates.isArray()){
                return new float[] {
                        (float) coordinates.get(1).asDouble(), // Latitude
                        (float) coordinates.get(0).asDouble()  // Longitude
                };
            }
        } catch (Exception e){
            System.err.println("Erro ao buscar coordenadas: " + e.getMessage());
        }
        return null;
    }
}
