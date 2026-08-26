package com.tienditayeya.tyback_end.dto;

import java.time.LocalDateTime;
import java.util.List;

public class CarritoDetalleDTO {
    private int idCarrito;
    private List<ItemCarritoDTO> items;
    // Getters y Setters
    public int getIdCarrito() { return idCarrito; }
    public void setIdCarrito(int idCarrito) { this.idCarrito = idCarrito; }
    public List<ItemCarritoDTO> getItems() { return items; }
    public void setItems(List<ItemCarritoDTO> items) { this.items = items; }
}

