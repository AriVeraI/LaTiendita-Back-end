package com.tienditayeya.tyback_end.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "productos_has_categorias")
@IdClass(ProductoHasCategoriaId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoHasCategoria {

    @Id
    @Column(name = "productos_id_productos", nullable = false)
    private Integer productosIdProductos;

    @Id
    @Column(name = "categorias_id_categoria", nullable = false)
    private Integer categoriasIdCategoria;
}