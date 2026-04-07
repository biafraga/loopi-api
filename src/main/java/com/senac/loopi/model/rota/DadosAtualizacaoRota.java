package com.senac.loopi.model.rota;

public record DadosAtualizacaoRota(
        String apelido,
        String origem,
        String destino,
        Float latitudeOrigem,
        Float longitudeOrigem,
        Float latitudeDestino,
        Float longitudeDestino
) {
}
