package com.tienditayeya.tyback_end.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "direcciones")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Direcciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion")
    private Integer idDireccion;

    @Column(name = "calle", length = 45, nullable = false)
    private String calle;

    @Column(name = "numero", length = 10, nullable = false)
    private String numero;

    @Column(name = "colonia", length = 45)
    private String colonia;

    @Column(name = "ciudad", length = 25, nullable = false)
    private String ciudad;

    @Column(name = "estado", length = 45, nullable = false)
    private String estado;

    @Column(name = "codigo_postal", length = 10, nullable = false)
    private String codigoPostal;

    @Column(name = "usuarios_id_usuario", nullable = false)
    private Integer usuarioId;


}