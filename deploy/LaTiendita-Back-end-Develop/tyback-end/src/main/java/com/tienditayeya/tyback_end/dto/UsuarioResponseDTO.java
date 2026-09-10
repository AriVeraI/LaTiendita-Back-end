package com.tienditayeya.tyback_end.dto;

import java.time.LocalDate;

public class UsuarioResponseDTO {

    private Long idUsuario;
    private String nombreCompleto;
    private String email;
    private String telefono;
    private LocalDate fechaRegistro;
    private Long idRol; // O podrías mostrar el nombre del rol más adelante

    public UsuarioResponseDTO() {}

    public UsuarioResponseDTO(Long idUsuario, String nombreCompleto, String email, String telefono, LocalDate fechaRegistro, Long idRol) {
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
        this.idRol = idRol;
    }

    // Getters y Setters
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public Long getIdRol() { return idRol; }
    public void setIdRol(Long idRol) { this.idRol = idRol; }
}
