package com.tienditayeya.tyback_end.dto;

import java.math.BigDecimal;
import java.util.List;

public record AnalyticsResponse(
        List<String> meses,
        List<BigDecimal> ventas,
        List<Long> pedidos
) {}
