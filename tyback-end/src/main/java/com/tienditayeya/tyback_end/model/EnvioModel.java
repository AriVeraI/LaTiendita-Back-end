package com.tienditayeya.tyback_end.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "envios")
public class EnvioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_envios")
    private Long id;

    @Column(name = "paqueteria", nullable = false, length = 45)
    private String paqueteria;

    @Column(name = "numero_rastreo", nullable = false, length = 45)
    private String numeroRastreo;

    @Column(name = "estado_envio", nullable = false, length = 45)
    private String estadoEnvio;

    @Column(name = "fecha_despacho", nullable = false)
    private LocalDateTime fechaDespacho;

    // El diagrama actual define esta columna como VARCHAR(45).
    @Column(name = "fecha_entrega_estimada", nullable = false, length = 45)
    private String fechaEntregaEstimada;

    @Column(name = "pedidos_id_pedidos", nullable = false)
    private Long pedidosIdPedidos;

    public EnvioModel() {
    }

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

    public String getNumeroRastreo() {
        return numeroRastreo;
    }

    public void setNumeroRastreo(String numeroRastreo) {
        this.numeroRastreo = numeroRastreo;
    }

    public String getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(String estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public LocalDateTime getFechaDespacho() {
        return fechaDespacho;
    }

    public void setFechaDespacho(LocalDateTime fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    public String getFechaEntregaEstimada() {
        return fechaEntregaEstimada;
    }

    public void setFechaEntregaEstimada(String fechaEntregaEstimada) {
        this.fechaEntregaEstimada = fechaEntregaEstimada;
    }

    public Long getPedidosIdPedidos() {
        return pedidosIdPedidos;
    }

    public void setPedidosIdPedidos(Long pedidosIdPedidos) {
        this.pedidosIdPedidos = pedidosIdPedidos;
    }
}
