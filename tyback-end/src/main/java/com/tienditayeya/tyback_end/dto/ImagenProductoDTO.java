package com.tienditayeya.tyback_end.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImagenProductoDTO {
    private Integer idImagenesProductos;
    private String urlImagen;
    private Integer productoId;
}
