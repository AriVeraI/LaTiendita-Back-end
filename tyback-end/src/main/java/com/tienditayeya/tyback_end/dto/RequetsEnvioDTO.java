package com.tienditayeya.tyback_end.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

// -- Se crea un Java Record para actuar como transportador de datos transparente que se genera automaticamente
public record RequestEnvioDTO(
        @NotBlank String paqueteria,
        @NotBlank String estadoDeEnvio,
        @NotBlank String numeroDeRastreo,
        @NotNull LocalDate fechaDespacho,
        @NotBlank LocalDate fechaEntregaEstimada,
        @NotNull Long idPedidos
){}

