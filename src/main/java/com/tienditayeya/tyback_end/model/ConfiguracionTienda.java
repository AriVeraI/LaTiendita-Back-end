package com.tienditayeya.tyback_end.model;

import jakarta.persistence.*;

@Entity
@Table(name="configuracion_tienda")
public class ConfiguracionTienda {
    @Id
    private Integer id;
    @Column(name="nombre_tienda", nullable=false, length=100)
    private String nombreTienda;
    @Column(name="correo_contacto", nullable=false, length=100)
    private String correoContacto;
    @Column(name="mensaje_bienvenida", length=255)
    private String mensajeBienvenida;
    @Column(length=100)
    private String instagram;
    @Column(length=100)
    private String tiktok;

    public Integer getId(){return id;} public void setId(Integer id){this.id=id;}
    public String getNombreTienda(){return nombreTienda;} public void setNombreTienda(String v){this.nombreTienda=v;}
    public String getCorreoContacto(){return correoContacto;} public void setCorreoContacto(String v){this.correoContacto=v;}
    public String getMensajeBienvenida(){return mensajeBienvenida;} public void setMensajeBienvenida(String v){this.mensajeBienvenida=v;}
    public String getInstagram(){return instagram;} public void setInstagram(String v){this.instagram=v;}
    public String getTiktok(){return tiktok;} public void setTiktok(String v){this.tiktok=v;}
}
