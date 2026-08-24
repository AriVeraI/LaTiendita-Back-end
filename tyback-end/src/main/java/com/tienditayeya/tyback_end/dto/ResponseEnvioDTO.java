package com.tienditayeya.tyback_end.dto;

import java.util.Date;

// -- Se crea un Java Record para actuar como transportador de datos transparente que se genera automaticamente
public record ResponseEnvioDTO(
        Long id,
        String paqueteria,
        Long numeroDeRastreo,
        String estadoDeEnvio,
        Date fechaDespacho,
        Date fechaEntregaEstimada
) {}
