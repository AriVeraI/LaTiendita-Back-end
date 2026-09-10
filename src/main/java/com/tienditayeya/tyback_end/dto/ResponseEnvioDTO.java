package com.tienditayeya.tyback_end.dto;

import java.time.LocalDateTime;

public record ResponseEnvioDTO(
        Long id,
        String paqueteria,
        String numeroRastreo,
        String estadoEnvio,
        LocalDateTime fechaDespacho,
        String fechaEntregaEstimada,
        Long pedidosIdPedidos
) {}
