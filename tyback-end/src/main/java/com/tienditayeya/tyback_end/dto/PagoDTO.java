package com.tienditayeya.tyback_end.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagoDTO {

    private Long idPagos; // Cambiado a Long
    private String metodoPago;
    private BigDecimal monto;
    private LocalDateTime fechaPago;
    private Long pedidoId; // Cambiado a Long

    // 1. Constructor vacío
    public PagoDTO() {
    }

    // 2. Constructor con todos los argumentos
    public PagoDTO(Long idPagos, String metodoPago, BigDecimal monto, LocalDateTime fechaPago, Long pedidoId) {
        this.idPagos = idPagos;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.pedidoId = pedidoId;
    }

    // 3. Getters y Setters
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

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }
}