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
    private Long idDireccion; // Cambiado a Long

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
    private Long usuarioId; // Cambiado a Long

    // Getters y Setters manuales por si Lombok te llega a fallar en algún mapeo
    public Long getIdDireccion() { return idDireccion; }
    public void setIdDireccion(Long idDireccion) { this.idDireccion = idDireccion; }
    public String getCalle() { return calle; }
    public void setCalle(String calle) { this.calle = calle; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getColonia() { return colonia; }
    public void setColonia(String colonia) { this.colonia = colonia; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getCodigoPostal() { return codigoPostal; }
    public void setCodigoPostal(String codigoPostal) { this.codigoPostal = codigoPostal; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
}