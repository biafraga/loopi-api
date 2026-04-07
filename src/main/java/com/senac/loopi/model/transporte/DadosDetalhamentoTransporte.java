package com.senac.loopi.model.transporte;

public record DadosDetalhamentoTransporte(
        Integer id,
        String tipo,
        int status,
        Integer rotaId
) {
    public DadosDetalhamentoTransporte(Transporte transporte){
        this(transporte.getId(), transporte.getTipo(), transporte.getStatus(), transporte.getRota().getId());
    }
}
