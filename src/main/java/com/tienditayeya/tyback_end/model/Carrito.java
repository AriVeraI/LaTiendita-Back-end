package com.tienditayeya.tyback_end.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "carrito")
public class Carrito {

    @Id //Autoincrement para el ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito")
    private int idCarrito;

    @Column(nullable = false, name = "fecha_creacion")
    private LocalDateTime fechaCracion;

    @Column(nullable = false, name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Column(nullable = false, name = "usuarios_id_usuario")
    private int idUsuario;

    //constructor vacío
    public Carrito(){

    }

    //Constructor con atributos

    public Carrito(int idCarrito, LocalDateTime fechaCracion, LocalDateTime fechaActualizacion, int idUsuario) {
        this.idCarrito = idCarrito;
        this.fechaCracion = fechaCracion;
        this.fechaActualizacion = fechaActualizacion;
        this.idUsuario = idUsuario;
    }


    //Getters & setters

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public LocalDateTime getFechaCracion() {
        return fechaCracion;
    }

    public void setFechaCracion(LocalDateTime fechaCracion) {
        this.fechaCracion = fechaCracion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }


    //toString


    @Override
    public String toString() {
        return "Carrito{" +
                "idCarrito=" + idCarrito +
                ", fechaCracion=" + fechaCracion +
                ", fechaActualizacion=" + fechaActualizacion +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
