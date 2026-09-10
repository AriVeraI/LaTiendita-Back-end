package com.tienditayeya.tyback_end.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;

    @NotBlank
    @Column(name = "rol_usuario", nullable = false, length = 20)
    private String rolUsuario;

    /**
     * Constructores
     */
    public Rol(){

    }

    public Rol(Long idRol, String rolUsuario) {
        this.idRol = idRol;
        this.rolUsuario = rolUsuario;
    }

    /**
     * GETTERS AND SETTERS
     */

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

    /**
     * toString
     */

    @Override
    public String toString() {
        return "Roles{" +
                "id_rol=" + idRol +
                ", rol_usuario='" + rolUsuario + '\'' +
                '}';
    }
}
