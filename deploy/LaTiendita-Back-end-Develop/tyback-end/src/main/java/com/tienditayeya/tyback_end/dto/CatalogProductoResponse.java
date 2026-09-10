package com.tienditayeya.tyback_end.dto;

import java.math.BigDecimal;
import java.util.List;

public record CatalogProductoResponse(
        Integer idProductos,
        String sku,
        String nombreProducto,
        String descripcionProducto,
        BigDecimal precio,
        Integer stock,
        String disponibilidad,
        List<String> categorias,
        String imagen
) {}
