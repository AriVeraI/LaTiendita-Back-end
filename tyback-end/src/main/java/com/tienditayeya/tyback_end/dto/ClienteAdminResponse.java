package com.tienditayeya.tyback_end.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ClienteAdminResponse(
        Long idUsuario,
        String nombreCompleto,
        String email,
        String telefono,
        LocalDate fechaRegistro,
        long pedidos,
        BigDecimal totalGastado
) {}
