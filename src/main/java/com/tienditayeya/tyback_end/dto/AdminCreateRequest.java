package com.tienditayeya.tyback_end.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AdminCreateRequest(
        @NotBlank @Size(max = 100) String nombre,
        @NotBlank @Email String correo,
        @NotBlank @Pattern(regexp = "\\d{10}") String telefono,
        @NotBlank @Size(min = 7, max = 72) String clave
) {}
