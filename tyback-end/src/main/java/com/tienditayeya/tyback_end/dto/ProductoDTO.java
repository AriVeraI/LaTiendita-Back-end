package com.tienditayeya.tyback_end.dto;

import java.math.BigDecimal;

public class ProductoDTO {

    private Integer idProductos;
    private String sku;
    private String nombreProducto;
    private String descripcionProducto;
    private BigDecimal precio;
    private Integer stock;
    private String disponibilidad;

    private String categoria;
    private String imagen;

    public ProductoDTO() {
    }

    public ProductoDTO(
            Integer idProductos,
            String sku,
            String nombreProducto,
            String descripcionProducto,
            BigDecimal precio,
            Integer stock,
            String disponibilidad,
            String categoria,
            String imagen) {

        this.idProductos = idProductos;
        this.sku = sku;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.precio = precio;
        this.stock = stock;
        this.disponibilidad = disponibilidad;
        this.categoria = categoria;
        this.imagen = imagen;
    }

    public Integer getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(Integer idProductos) {
        this.idProductos = idProductos;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}