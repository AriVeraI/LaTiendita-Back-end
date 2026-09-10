package com.tienditayeya.tyback_end.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoAdminResponse(
        Long idPedidos,
        Integer numeroPedido,
        String cliente,
        String email,
        List<String> productos,
        BigDecimal total,
        String estado,
        LocalDateTime fecha,
        String numeroRastreo,
        String estadoEnvio
) {}
