package com.tienditayeya.tyback_end.dto;

public class ItemCarritoDTO {
    private Long idCarritoProductos;
    private int cantidad;
    private int idProducto;
    // Aquí podrías agregar nombre, precio, imagen si gustas mapearlo desde tu tabla productos

    // Getters y Setters
    public Long getIdCarritoProductos() { return idCarritoProductos; }
    public void setIdCarritoProductos(Long idCarritoProductos) { this.idCarritoProductos = idCarritoProductos; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }


}