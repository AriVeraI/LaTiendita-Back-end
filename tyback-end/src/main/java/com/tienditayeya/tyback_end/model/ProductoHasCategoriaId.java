package com.tienditayeya.tyback_end.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoHasCategoriaId implements Serializable {
    private Integer productosIdProductos;
    private Integer categoriasIdCategoria;
}