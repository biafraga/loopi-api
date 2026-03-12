package com.senac.loopi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "alerta")
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
    @JsonIgnore
    private Rota rota;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getHorarioChegada() {
        return horarioChegada;
    }

    public void setHorarioChegada(LocalDateTime horarioChegada) {
        this.horarioChegada = horarioChegada;
    }

    public String getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(String diasSemana) {
        this.diasSemana = diasSemana;
    }

    public int getAntecedenciaMinutos() {
        return antecedenciaMinutos;
    }

    public void setAntecedenciaMinutos(int antecedenciaMinutos) {
        this.antecedenciaMinutos = antecedenciaMinutos;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Rota getRota() {
        return rota;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }
}
