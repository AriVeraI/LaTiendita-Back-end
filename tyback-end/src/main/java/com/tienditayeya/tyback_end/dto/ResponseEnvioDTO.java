package com.tienditayeya.tyback_end.dto;

import java.time.LocalDate;

public record ResponseEnvioDTO(
        Long id,
        String paqueteria,
        String numeroDeRastreo,
        String estadoDeEnvio,
        LocalDate fechaDespacho,
        LocalDate fechaEntregaEstimada,
        Long pedidoId
) {}