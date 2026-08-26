package com.tienditayeya.tyback_end.dto;

public class ActualizarCantidadDTO {
    private Long idCarritoProductos;
    private int cantidad;
    private int idUsuario;

    // Getters y Setters
    public Long getIdCarritoProductos() { return idCarritoProductos; }
    public void setIdCarritoProductos(Long idCarritoProductos) { this.idCarritoProductos = idCarritoProductos; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
}
