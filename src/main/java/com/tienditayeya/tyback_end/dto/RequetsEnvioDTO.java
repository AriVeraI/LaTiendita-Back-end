package com.tienditayeya.tyback_end.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RequetsEnvioDTO(
        @NotBlank String paqueteria,
        @NotBlank String numeroRastreo,
        @NotNull LocalDateTime fechaDespacho,
        @NotBlank String fechaEntregaEstimada,
        @NotNull Long pedidosIdPedidos
) {}
