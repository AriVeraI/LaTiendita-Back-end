package com.tienditayeya.tyback_end.model;

import jakarta.persistence.*;

@Entity
@Table(name = "imagenes_productos")
public class ImagenProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagenes_productos")
    private Long idImagenesProductos;

    @Column(name = "url_imagen", nullable = false, length = 100)
    private String urlImagen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productos_id_productos", nullable = false)
    private Producto producto;

    // Constructor vacío
    public ImagenProducto() {
    }

    //Constructor con todos los parámetros
    public ImagenProducto(Long idImagenesProductos, String urlImagen, Long productosIdProductos) {
        this.idImagenesProductos = idImagenesProductos;
        this.urlImagen = urlImagen;
        this.producto = producto;
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}