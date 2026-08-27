package com.tienditayeya.tyback_end.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedidos")
    private Long idPedidos;

    @Column(name = "numero_pedido", nullable = false, length = 45)
    private String numeroPedido;

    @Column(name = "total", nullable = false, precision = 5, scale = 2)
    private BigDecimal total;

    @Column(name = "estado_pedido", nullable = false, length = 45)
    private String estadoPedido;

    @Column(name = "fecha_creacion_pedido")
    private LocalDateTime fechaCreacionPedido;

    // Relación ManyToOne con Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarios_id_pedidos", nullable = false)
    private Usuario usuario;

    // Relación ManyToOne con Direcciones
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "direcciones_id_direccion")
//    private Direcciones direccion;

    // Constructores
    public Pedido() {
    }

    public Pedido(String numeroPedido, BigDecimal total, String estadoPedido, LocalDateTime fechaCreacionPedido, Usuario usuario, Direcciones direccion) {
        this.numeroPedido = numeroPedido;
        this.total = total;
        this.estadoPedido = estadoPedido;
        this.fechaCreacionPedido = fechaCreacionPedido;
        this.usuario = usuario;
//        this.direccion = direccion;
    }

    // Getters y Setters
    public Long getIdPedidos() {
        return idPedidos;
    }

    public void setIdPedidos(Long idPedidos) {
        this.idPedidos = idPedidos;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public LocalDateTime getFechaCreacionPedido() {
        return fechaCreacionPedido;
    }

    public void setFechaCreacionPedido(LocalDateTime fechaCreacionPedido) {
        this.fechaCreacionPedido = fechaCreacionPedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

//    public Direcciones getDireccion() {
//        return direccion;
//    }
//
//    public void setDireccion(Direcciones direccion) {
//        this.direccion = direccion;
//    }
}