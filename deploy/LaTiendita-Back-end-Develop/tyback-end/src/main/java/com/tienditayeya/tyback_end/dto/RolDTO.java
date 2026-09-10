package com.tienditayeya.tyback_end.dto;

import jakarta.validation.constraints.NotBlank;


public class RolDTO {

    private Long idRol;

    @NotBlank(message = "El nombre del rol es obligatorio")
    private String rolUsuario;

    public RolDTO(){

    }

    public RolDTO(Long idRol, String rolUsuario){
        this.idRol = idRol;
        this.rolUsuario = rolUsuario;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }
}
