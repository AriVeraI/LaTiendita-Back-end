package com.tienditayeya.tyback_end.model;

import jakarta.persistence.*;

@Entity
@Table(name = "imagenes_productos")
public class ImagenProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagenes_productos")
    private Long idImagenesProductos; // Cambiado a Long

    @Column(name = "url_imagen", nullable = false, length = 50)
    private String urlImagen;

    @Column(name = "productos_id_productos", nullable = false)
    private Long productosIdProductos; // Cambiado a Long

    // Constructor vacío (Obligatorio para JPA/Spring)
    public ImagenProducto() {
    }

    //Constructor con todos los parámetros
    public ImagenProducto(Long idImagenesProductos, String urlImagen, Long productosIdProductos) {
        this.idImagenesProductos = idImagenesProductos;
        this.urlImagen = urlImagen;
        this.productosIdProductos = productosIdProductos;
    }

    // Getters y Setters
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

    public Long getProductosIdProductos() {
        return productosIdProductos;
    }

    public void setProductosIdProductos(Long productosIdProductos) {
        this.productosIdProductos = productosIdProductos;
    }
}