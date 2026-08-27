package com.tienditayeya.tyback_end.dto;

import java.time.LocalDateTime;

public class PagoDTO {

    private Long idPagos;
    private String metodoPago;
    private Double monto;
    private LocalDateTime fechaPago;
    private Long pedidoId;

    public PagoDTO() {
    }

    public PagoDTO(Long idPagos, String metodoPago, Double monto, LocalDateTime fechaPago, Long pedidoId) {
        this.idPagos = idPagos;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.pedidoId = pedidoId;
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

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }
}