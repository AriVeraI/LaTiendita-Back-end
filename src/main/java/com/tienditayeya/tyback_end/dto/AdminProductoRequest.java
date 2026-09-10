package com.tienditayeya.tyback_end.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record AdminProductoRequest(
        @NotBlank @Size(max = 45) String nombreProducto,
        @NotBlank @Size(max = 150) String descripcionProducto,
        @NotNull @DecimalMin(value = "0.01") BigDecimal precio,
        @NotNull @Min(0) Integer stock,
        @NotBlank String tipoPieza,
        @NotNull Integer categoriaId,
        @NotBlank @Size(max = 50) String imagen
) {}
