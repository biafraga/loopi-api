package com.senac.loopi.model.alerta;

import java.time.LocalDateTime;

public record DadosDetalhamentoAlerta(
        Integer id,
        LocalDateTime horarioChegada,
        String diasSemana,
        int antecedenciaMinutos,
        int status,
        Integer rotaId
) {
    public DadosDetalhamentoAlerta(Alerta alerta){
        this(alerta.getId(), alerta.getHorarioChegada(), alerta.getDiasSemana(),
                alerta.getAntecedenciaMinutos(), alerta.getStatus(), alerta.getRota().getId());
    }
}
