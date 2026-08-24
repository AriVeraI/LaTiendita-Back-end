package com.tienditayeya.tyback_end.model;

import jakarta.persistence.*;

@Entity
@Table(name = "carrito_productos")
public class CarritoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito_productos")
    private Long idCarritoProductos;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "productos_id_productos", nullable = false)
    private int productosIdProductos;

    @Column(name = "carrito_id_carrito", nullable = false)
    private int carritoIdCarrito;

    // Constructor vacío requerido por JPA
    public CarritoProducto() {
    }

    // Constructor con parámetros
    public CarritoProducto(Long idCarritoProductos, int cantidad, int productosIdProductos, int carritoIdCarrito) {
        this.idCarritoProductos = idCarritoProductos;
        this.cantidad = cantidad;
        this.productosIdProductos = productosIdProductos;
        this.carritoIdCarrito = carritoIdCarrito;
    }

    // Getters y Setters
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

    @Override
    public String toString() {
        return "CarritoProducto{" +
                "idCarritoProductos=" + idCarritoProductos +
                ", cantidad=" + cantidad +
                ", productosIdProductos=" + productosIdProductos +
                ", carritoIdCarrito=" + carritoIdCarrito +
                '}';
    }
}