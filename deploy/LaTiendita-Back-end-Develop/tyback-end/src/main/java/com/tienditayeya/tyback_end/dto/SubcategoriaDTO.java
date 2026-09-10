package com.tienditayeya.tyback_end.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubcategoriaDTO {
    private Integer idSubcategoria;
    private String nombreSubcategoria;
    private Integer idCategoria; // Guardamos solo el ID de la categoría relacionada para evitar bucles
}