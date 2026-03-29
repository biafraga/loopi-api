package com.senac.loopi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "alerta")
@Getter
@Setter
public class Alerta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alerta_id", nullable = false)
    private int id;
    @Column(name = "alerta_horario_chegada", nullable = false)
    private LocalDateTime horarioChegada;
    @Column(name = "alerta_dias_semana", nullable = false)
    private String diasSemana;
    @Column(name = "alerta_antecedencia_minutos", nullable = false)
    private int antecedenciaMinutos;
    @Column(name = "alerta_status", nullable = false)
    private int status;

    @ManyToOne
    @JoinColumn(name = "rota_id", nullable = false)
    private Rota rota;


}
