package com.tienditayeya.tyback_end.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

@Entity
@Table(name = "detalle_pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetallesPedido {
    //id autogenerado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle_pedido;
    //Columna para la cantidad de productos agregados
    @Column(nullable = false)
    private Integer cantidad;
    //Columna para el precio total unitario
    @Column(nullable = false)
    private Double precioUnitario;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedidos pedidos;
}
