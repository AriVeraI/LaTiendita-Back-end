package com.tienditayeya.tyback_end.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name = "pedidos")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pedido;

    @Column(nullable = false)
    private Long numeroPedido;

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false)
    private String estadoPedido;

    @Column(nullable = false)
    private Date fechaPedido = new Date();
}
