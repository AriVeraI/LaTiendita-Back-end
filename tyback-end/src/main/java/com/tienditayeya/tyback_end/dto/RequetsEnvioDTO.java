package com.tienditayeya.tyback_end.dto;

import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

// -- Se crea un Java Record para actuar como transportador de datos transparente que se genera automaticamente
public record RequetsEnvioDTO(
        @NotNull String paqueteria,
        @NotNull String estadoDeEnvio, // 👈 Agrega esta línea
        @NotNull Long numeroDeRastreo,
        @NotNull Date fechaDespacho,
        @NotNull Date fechaEntregaEstimada
){}
