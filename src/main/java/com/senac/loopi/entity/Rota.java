package com.senac.loopi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rota")
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
    @JsonIgnore
    private Usuario usuario;

    @OneToMany(mappedBy = "rota")
    private List<Alerta> alertas;

    @OneToMany(mappedBy = "rota")
    private List<Transporte> transportes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Float getLatitudeOrigem() {
        return latitudeOrigem;
    }

    public void setLatitudeOrigem(Float latitudeOrigem) {
        this.latitudeOrigem = latitudeOrigem;
    }

    public Float getLongitudeOrigem() {
        return longitudeOrigem;
    }

    public void setLongitudeOrigem(Float longitudeOrigem) {
        this.longitudeOrigem = longitudeOrigem;
    }

    public Float getLatitudeDestino() {
        return latitudeDestino;
    }

    public void setLatitudeDestino(Float latitudeDestino) {
        this.latitudeDestino = latitudeDestino;
    }

    public Float getLongitudeDestino() {
        return longitudeDestino;
    }

    public void setLongitudeDestino(Float longitudeDestino) {
        this.longitudeDestino = longitudeDestino;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Alerta> getAlertas() {
        return alertas;
    }

    public void setAlertas(List<Alerta> alertas) {
        this.alertas = alertas;
    }

    public List<Transporte> getTransportes() {
        return transportes;
    }

    public void setTransportes(List<Transporte> transportes) {
        this.transportes = transportes;
    }
}
