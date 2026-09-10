package com.tienditayeya.tyback_end.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CrearPedidoRequest(
        @NotNull Long idUsuario,
        @NotEmpty List<@Valid ItemPedidoRequest> items,
        @NotNull @Valid DireccionPedidoRequest direccion,
        @NotNull @Valid PagoPedidoRequest pago
) {
    public record ItemPedidoRequest(
            @NotNull Integer idProducto,
            @NotNull @Positive Integer cantidad
    ) {}

    public record DireccionPedidoRequest(
            @NotBlank @Size(min = 3, max = 100) String nombreCompleto,
            @NotBlank @Size(max = 45) String calle,
            @NotBlank @Pattern(regexp = "^(?!0+$)[A-Za-z0-9-]{1,10}$", message = "El número exterior no puede ser 0 y solo admite letras, números o guion") String numero,
            @NotBlank @Size(max = 45) String colonia,
            @NotBlank @Size(max = 25) @Pattern(regexp = "^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ .-]{2,25}$", message = "La ciudad debe contener principalmente letras") String ciudad,
            @NotBlank @Size(max = 45) @Pattern(regexp = "^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ .-]{2,45}$", message = "El estado debe contener principalmente letras") String estado,
            @NotBlank @Pattern(regexp = "\\d{5}", message = "El código postal debe tener 5 dígitos") String codigoPostal,
            @NotBlank @Pattern(
                    regexp = "^[0-9+()\\-\\s]{10,20}$",
                    message = "El teléfono debe contener entre 10 y 20 caracteres válidos"
            ) String telefono
    ) {}

    public record PagoPedidoRequest(
            @NotBlank @Size(max = 45) String metodoPago
    ) {}
}
