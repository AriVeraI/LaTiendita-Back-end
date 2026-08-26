package com.tienditayeya.tyback_end.dto;


public class CarritoProductoDTO {

    private Long idCarritoProductos;
    private int cantidad;
    private int productosIdProductos;
    private int carritoIdCarrito;

    public CarritoProductoDTO() {
    }

    public CarritoProductoDTO(Long idCarritoProductos, int cantidad, int productosIdProductos, int carritoIdCarrito) {
        this.idCarritoProductos = idCarritoProductos;
        this.cantidad = cantidad;
        this.productosIdProductos = productosIdProductos;
        this.carritoIdCarrito = carritoIdCarrito;
    }

    public Long getIdCarritoProductos() {
        return idCarritoProductos;
    }

    public void setIdCarritoProductos(Long idCarritoProductos) {
        this.idCarritoProductos = idCarritoProductos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getProductosIdProductos() {
        return productosIdProductos;
    }

    public void setProductosIdProductos(int productosIdProductos) {
        this.productosIdProductos = productosIdProductos;
    }

    public int getCarritoIdCarrito() {
        return carritoIdCarrito;
    }

    public void setCarritoIdCarrito(int carritoIdCarrito) {
        this.carritoIdCarrito = carritoIdCarrito;
    }
}