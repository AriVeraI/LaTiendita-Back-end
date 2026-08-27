package com.tienditayeya.tyback_end.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "envios")
public class EnvioModel {
    // -- Establecemos los valores de la tabla Envio
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="paqueteria", nullable = false, length = 45)
    private String paqueteria;

    @Column(name="numero_rastreo", nullable = false, length = 45, unique = true)
    private String numeroDeRastreo;

    @Column(name="estado_envio", nullable = false, length = 45)
    private String estadoDeEnvio;

    @Column(name="fecha_despacho", nullable = false)
    private LocalDate fechaDespacho;

    @Column(nullable = false, length = 45)
    private LocalDate fechaEntregaEstimada;

    //LLave foranea
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedidos_id_pedidos", nullable = false)
    private Pedido pedido;

    public EnvioModel() {

    }


    // -- Se crea los constructores


    public EnvioModel(String paqueteria, String numeroDeRastreo, String estadoDeEnvio, LocalDate fechaDespacho, LocalDate fechaEntregaEstimada, Pedido pedido) {
        this.paqueteria = paqueteria;
        this.numeroDeRastreo = numeroDeRastreo;
        this.estadoDeEnvio = estadoDeEnvio;
        this.fechaDespacho = fechaDespacho;
        this.fechaEntregaEstimada = fechaEntregaEstimada;
        this.pedido = pedido;
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

    public String getNumeroDeRastreo() {
        return numeroDeRastreo;
    }

    public void setNumeroDeRastreo(String numeroDeRastreo) {
        this.numeroDeRastreo = numeroDeRastreo;
    }

    public String getEstadoDeEnvio() {
        return estadoDeEnvio;
    }

    public void setEstadoDeEnvio(String estadoDeEnvio) {
        this.estadoDeEnvio = estadoDeEnvio;
    }

    public LocalDate getFechaDespacho() {
        return fechaDespacho;
    }

    public void setFechaDespacho(LocalDate fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    public LocalDate getFechaEntregaEstimada() {
        return fechaEntregaEstimada;
    }

    public void setFechaEntregaEstimada(LocalDate fechaEntregaEstimada) {
        this.fechaEntregaEstimada = fechaEntregaEstimada;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}