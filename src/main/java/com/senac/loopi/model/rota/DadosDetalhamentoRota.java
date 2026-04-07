package com.senac.loopi.model.rota;

public record DadosDetalhamentoRota(
        int id,
        String apelido,
        String origem,
        String destino,
        Float latitudeOrigem,
        Float longitudeOrigem,
        Float latitudeDestino,
        Float longitudeDestino,
        int status,
        Integer usuarioId
) {
    public DadosDetalhamentoRota(Rota rota){
        this(rota.getId(), rota.getApelido(), rota.getOrigem(), rota.getDestino(),
                rota.getLatitudeOrigem(), rota.getLongitudeOrigem(), rota.getLatitudeDestino(),
                rota.getLongitudeDestino(), rota.getStatus(), rota.getUsuario().getId());
    }

}
