package com.tienditayeya.tyback_end.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagos")
    private Long idPagos; // Cambiado a Long

    @Column(name = "metodo_pago", nullable = false, length = 45)
    private String metodoPago;

    @Column(name = "monto", nullable = false)
    private Double monto;

    @Column(name = "fecha_pago", nullable = false)
    private LocalDateTime fechaPago;

    @Column(name = "pedidos_id_pedidos", nullable = false)
    private Long pedidosIdPedidos; // Cambiado a Long para hacer match

    // Constructor vacío (Obligatorio para JPA/Spring)
    public Pago() {
    }

    // Constructor con todos los parámetros
    public Pago(Long idPagos, String metodoPago, Double monto, LocalDateTime fechaPago, Long pedidosIdPedidos) {
        this.idPagos = idPagos;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.pedidosIdPedidos = pedidosIdPedidos;
    }

    // Getters y Setters
    public Long getIdPagos() {
        return idPagos;
    }

    public void setIdPagos(Long idPagos) {
        this.idPagos = idPagos;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Long getPedidosIdPedidos() {
        return pedidosIdPedidos;
    }

    public void setPedidosIdPedidos(Long pedidosIdPedidos) {
        this.pedidosIdPedidos = pedidosIdPedidos;
    }
}