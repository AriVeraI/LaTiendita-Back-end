package com.tienditayeya.tyback_end.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetallePedidoDTO {

    private Long idDetallePedido;
    private Integer cantidad;
    private BigDecimal precioTotalUnitario;
    private Long pedidosIdPedidos;
    private Long productosIdProductos;
}