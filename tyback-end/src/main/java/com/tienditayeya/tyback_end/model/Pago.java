package com.tienditayeya.tyback_end.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagos")
    private Long idPagos;

    @Column(name = "metodo_pago", nullable = false, length = 45)
    private String metodoPago;

    @Column(name = "monto", nullable = false)
    private Double monto;

    @Column(name = "fecha_pago", nullable = false)
    private LocalDateTime fechaPago;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedidos_id_pedidos", nullable = false)
    private Pedido pedido;

    public Pago() {
    }

    public Pago(Long idPagos, String metodoPago, Double monto, LocalDateTime fechaPago, Pedido pedido) {
        this.idPagos = idPagos;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.pedido = pedido;
    }

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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}