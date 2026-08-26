package com.tienditayeya.tyback_end.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "envio")
public class EnvioModel {
    // -- Establecemos los valores de la tabla Envio
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String paqueteria;

    @Column(nullable = false, length = 45)
    private Long numeroDeRastreo;

    @Column(nullable = false, length = 45)
    private String estadoDeEnvio;

    @Column(nullable = false)
    private Date fechaDespacho;

    @Column(nullable = false, length = 45)
    private Date fechaEntregaEstimada;

    public EnvioModel() {

    }


    // -- Se crea los constructores
    public EnvioModel(String paqueteria, Long numeroDeRastreo, String estadoDeEnvio, Date fechaDespacho, Date fechaEntregaEstimada) {
        this.paqueteria = paqueteria;
        this.numeroDeRastreo = numeroDeRastreo;
        this.estadoDeEnvio = estadoDeEnvio;
        this.fechaDespacho = fechaDespacho;
        this.fechaEntregaEstimada = fechaEntregaEstimada;
    }

    // -- Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaqueteria() {
        return paqueteria;
    }

    public void setPaqueteria(String paqueteria) {
        this.paqueteria = paqueteria;
    }

    public Long getNumeroDeRastreo() {
        return numeroDeRastreo;
    }

    public void setNumeroDeRastreo(Long numeroDeRastreo) {
        this.numeroDeRastreo = numeroDeRastreo;
    }

    public String getEstadoDeEnvio() {
        return estadoDeEnvio;
    }

    public void setEstadoDeEnvio(String estadoDeEnvio) {
        this.estadoDeEnvio = estadoDeEnvio;
    }

    public Date getFechaDespacho() {
        return fechaDespacho;
    }

    public void setFechaDespacho(Date fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    public Date getFechaEntregaEstimada() {
        return fechaEntregaEstimada;
    }

    public void setFechaEntregaEstimada(Date fechaEntregaEstimada) {
        this.fechaEntregaEstimada = fechaEntregaEstimada;
    }
}
