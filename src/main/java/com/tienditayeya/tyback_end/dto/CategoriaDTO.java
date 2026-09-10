package com.tienditayeya.tyback_end.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {
    private Integer idCategoria;
    private String nombreCategoria;
    private String slug;
}