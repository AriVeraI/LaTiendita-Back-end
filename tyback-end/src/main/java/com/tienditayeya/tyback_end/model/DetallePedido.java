package com.tienditayeya.tyback_end.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_pedido")
    private Long idDetallePedido;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_total_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioTotalUnitario;

    @Column(name = "pedidos_id_pedidos", nullable = false)
    private Long pedidosIdPedidos;

    @Column(name = "productos_id_productos", nullable = false)
    private Integer productosIdProductos;

    public DetallePedido() {
    }

    public Long getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(Long idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioTotalUnitario() {
        return precioTotalUnitario;
    }

    public void setPrecioTotalUnitario(BigDecimal precioTotalUnitario) {
        this.precioTotalUnitario = precioTotalUnitario;
    }

    public Long getPedidosIdPedidos() {
        return pedidosIdPedidos;
    }

    public void setPedidosIdPedidos(Long pedidosIdPedidos) {
        this.pedidosIdPedidos = pedidosIdPedidos;
    }

    public Integer getProductosIdProductos() {
        return productosIdProductos;
    }

    public void setProductosIdProductos(Integer productosIdProductos) {
        this.productosIdProductos = productosIdProductos;
    }
}
