package com.senac.loopi.model.alerta;

import java.time.LocalDateTime;

public record DadosAtualizacaoAlerta(
        LocalDateTime horarioChegada,
        String diasSemana,
        int antecedenciaMinutos
) {
}
