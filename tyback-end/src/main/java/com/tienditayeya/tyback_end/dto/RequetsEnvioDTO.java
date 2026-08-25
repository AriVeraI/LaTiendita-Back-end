package com.tienditayeya.tyback_end.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

// -- Se crea un Java Record para actuar como transportador de datos transparente que se genera automaticamente
public record RequetsEnvioDTO(
        @NotNull
        String paqueteria,
        @NotNull
        Long numeroDeRastreo,
        @NotNull
        Date fechaDespacho,
        @NotNull
        Date fechaEntregaEstimada
){}
