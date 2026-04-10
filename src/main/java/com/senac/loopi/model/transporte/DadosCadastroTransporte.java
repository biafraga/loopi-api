package com.senac.loopi.model.transporte;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTransporte(
        @NotBlank
        String tipo,
        @NotNull
        Integer rotaId
) {
}
