package com.tienditayeya.tyback_end.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DireccionesDTO {
    private Long idDireccion;
    private String calle;
    private String numero;
    private String colonia;
    private String ciudad;
    private String estado;
    private String codigoPostal;
    private Long usuariosId;
}