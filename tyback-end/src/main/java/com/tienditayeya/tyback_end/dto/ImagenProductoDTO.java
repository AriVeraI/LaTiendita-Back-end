package com.tienditayeya.tyback_end.dto;

public class ImagenProductoDTO {

    private Long idImagenesProductos; // Cambiado a Long
    private String urlImagen;
    private Long productoId; // Cambiado a Long

    // 1. Constructor vacío
    public ImagenProductoDTO() {
    }

    // 2. Constructor con todos los argumentos
    public ImagenProductoDTO(Long idImagenesProductos, String urlImagen, Long productoId) {
        this.idImagenesProductos = idImagenesProductos;
        this.urlImagen = urlImagen;
        this.productoId = productoId;
    }

    // 3. Getters y Setters
    public Long getIdImagenesProductos() {
        return idImagenesProductos;
    }

    public void setIdImagenesProductos(Long idImagenesProductos) {
        this.idImagenesProductos = idImagenesProductos;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }
}
