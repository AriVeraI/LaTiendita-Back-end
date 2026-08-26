package com.tienditayeya.tyback_end.dto;

import java.time.LocalDateTime;

public class CarritoDTO {

    private int idCarrito;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private int idUsuario;

    // Constructores
    public CarritoDTO() {
    }

    public CarritoDTO(int idCarrito, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion, int idUsuario) {
        this.idCarrito = idCarrito;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.idUsuario = idUsuario;
    }

    // Getters y Setters
    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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
}