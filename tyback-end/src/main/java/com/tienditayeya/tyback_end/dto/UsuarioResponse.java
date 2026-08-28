package com.tienditayeya.tyback_end.dto;

import java.time.LocalDate;

public record UsuarioResponse(
        Long idUsuario,
        String nombreCompleto,
        String email,
        String telefono,
        LocalDate fechaRegistro,
        String rol,
        String sessionToken
) {}
