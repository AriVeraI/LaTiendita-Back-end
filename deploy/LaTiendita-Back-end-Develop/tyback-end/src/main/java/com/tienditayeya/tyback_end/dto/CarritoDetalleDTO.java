package com.tienditayeya.tyback_end.dto;

import java.time.LocalDateTime;
import java.util.List;

public class CarritoDetalleDTO {
    private int idCarrito;
    private LocalDateTime fechaCreacion;
    private int idUsuario;
    private List<ItemCarritoDTO> items; // Lista de productos dentro del carrito

    // Getters y Setters
    public int getIdCarrito() { return idCarrito; }
    public void setIdCarrito(int idCarrito) { this.idCarrito = idCarrito; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public List<ItemCarritoDTO> getItems() { return items; }
    public void setItems(List<ItemCarritoDTO> items) { this.items = items; }
}

