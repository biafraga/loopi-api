package com.senac.loopi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transporte")
@Getter
@Setter
public class Transporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transporte_id", nullable = false)
    private int id;
    @Column(name = "transporte_tipo", nullable = false)
    private String tipo;
    @Column(name = "transporte_status", nullable = false)
    private int status;

    @ManyToOne
    @JoinColumn(name = "rota_id", nullable = false)
    private Rota rota;


}
