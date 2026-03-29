package com.senac.loopi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id", nullable = false)
    private int id;
    @Column (name = "usuario_nome", nullable = false)
    private String nome;
    @Column(name = "usuario_email", nullable = false)
    private String email;
    @Column(name = "usuario_senha", nullable = false)
    private String senha;
    @Column(name = "usuario_status", nullable = false)
    private int status;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Rota> rotas;

}
