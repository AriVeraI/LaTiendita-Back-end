package com.tienditayeya.tyback_end.dto;

import java.math.BigDecimal;

public record AdminSummaryResponse(
        BigDecimal ventasTotales,
        long pedidosMes,
        long clientes,
        long productos,
        long stockBajo
) {}
