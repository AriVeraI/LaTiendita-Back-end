package com.tienditayeya.tyback_end.dto;

import java.math.BigDecimal;

public class ItemCarritoDTO {
    private Long idCarritoProductos;
    private int cantidad;
    private int idProducto;
    private String nombreProducto;
    private BigDecimal precioUnitario;

    // Aquí podrías agregar nombre, precio, imagen si gustas mapearlo desde tu tabla productos

    // Getters y Setters
    public Long getIdCarritoProductos() { return idCarritoProductos; }
    public void setIdCarritoProductos(Long idCarritoProductos) { this.idCarritoProductos = idCarritoProductos; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}