package com.tienditayeya.tyback_end.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "subcategoria")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subcategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subcategoria")
    private Integer idSubcategoria;

    @Column(name = "nombre_subcategoria", nullable = false, length = 45)
    private String nombreSubcategoria;

    // Relación Many-to-One: Muchas subcategorías pueden pertenecer a una misma categoría
    @ManyToOne
    @JoinColumn(name = "categorias_id_categoria", nullable = false)
    private Categoria categoria;
}