package com.tienditayeya.tyback_end.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoHasCategoriaDTO {
    private Integer productosIdProductos;
    private Integer categoriasIdCategoria;
}