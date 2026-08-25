package com.tienditayeya.tyback_end.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PedidoDTO {

    private Long idPedidos;
    private String numeroPedido;
    private BigDecimal total;
    private String estadoPedido;
    private LocalDateTime fechaCreacionPedido;
    private Long idUsuario;
    private Long idDireccion;

    public PedidoDTO() {
    }

    // Getters y Setters
    public Long getIdPedidos() { return idPedidos; }
    public void setIdPedidos(Long idPedidos) { this.idPedidos = idPedidos; }

    public String getNumeroPedido() { return numeroPedido; }
    public void setNumeroPedido(String numeroPedido) { this.numeroPedido = numeroPedido; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public String getEstadoPedido() { return estadoPedido; }
    public void setEstadoPedido(String estadoPedido) { this.estadoPedido = estadoPedido; }

    public LocalDateTime getFechaCreacionPedido() { return fechaCreacionPedido; }
    public void setFechaCreacionPedido(LocalDateTime fechaCreacionPedido) { this.fechaCreacionPedido = fechaCreacionPedido; }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public Long getIdDireccion() { return idDireccion; }
    public void setIdDireccion(Long idDireccion) { this.idDireccion = idDireccion; }
}