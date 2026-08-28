package com.tienditayeya.tyback_end.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PedidoResponse(
        Long idPedidos,
        Integer numeroPedido,
        BigDecimal total,
        String estadoPedido,
        LocalDateTime fechaCreacionPedido,
        Long idUsuario
) {}
