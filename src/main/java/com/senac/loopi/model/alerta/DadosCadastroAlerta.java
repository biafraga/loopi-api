package com.senac.loopi.model.alerta;

import java.time.LocalDateTime;

public record DadosCadastroAlerta(
        LocalDateTime horarioChegada,
        String diasSemana,
        int antecedenciaMinutos,
        Integer rotaId // Para sabermos a qual rota este alerta pertence
) {
}
