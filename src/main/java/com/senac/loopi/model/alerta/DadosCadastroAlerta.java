package com.senac.loopi.model.alerta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCadastroAlerta(
        @NotNull
        LocalDateTime horarioChegada,
        @NotBlank
        String diasSemana,
        @NotNull
        int antecedenciaMinutos,
        @NotNull
        Integer rotaId // Para sabermos a qual rota este alerta pertence
) {
}
