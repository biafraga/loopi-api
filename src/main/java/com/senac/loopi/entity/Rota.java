package com.senac.loopi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "rota")
@Getter
@Setter
public class Rota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rota_id", nullable = false)
    private int id;
    @Column(name = "rota_apelido", nullable = false)
    private String apelido;
    @Column(name = "rota_origem", nullable = false)
    private String origem;
    @Column(name = "rota_destino", nullable = false)
    private String destino;
    @Column(name = "rota_latitude_origem", nullable = false)
    private Float latitudeOrigem;
    @Column(name = "rota_longitude_origem", nullable = false)
    private Float longitudeOrigem;
    @Column(name = "rota_latitude_destino", nullable = false)
    private Float latitudeDestino;
    @Column(name = "rota_longitude_destino", nullable = false)
    private Float longitudeDestino;
    @Column(name = "rota_status", nullable = false)
    private int status;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "rota")
    @JsonIgnore
    private List<Alerta> alertas;

    @OneToMany(mappedBy = "rota")
    @JsonIgnore
    private List<Transporte> transportes;

}
