package com.senac.loopi.model.rota;

public record DadosCadastroRota(
        String apelido,
        String origem,
        String destino,
        Float latitudeOrigem,
        Float longitudeOrigem,
        Float latitudeDestino,
        Float longitudeDestino,
        Integer usuarioId //para sabermos quem é o dono da rota
) {
}
