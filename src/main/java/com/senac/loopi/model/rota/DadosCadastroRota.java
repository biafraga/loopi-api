package com.senac.loopi.model.rota;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroRota(
        @NotBlank
        String apelido,
        @NotBlank
        String origem,
        @NotBlank
        String destino,
        @NotNull
        Float latitudeOrigem,
        @NotNull
        Float longitudeOrigem,
        @NotNull
        Float latitudeDestino,
        @NotNull
        Float longitudeDestino,
        @NotNull
        Integer usuarioId //para sabermos quem é o dono da rota
) {
}
