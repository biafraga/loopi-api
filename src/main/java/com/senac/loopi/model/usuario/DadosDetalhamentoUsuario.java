package com.senac.loopi.model.usuario;

public record DadosDetalhamentoUsuario(
        int id,
        String nome,
        String email,
        int status
) {
    public DadosDetalhamentoUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getStatus());
    }
}
