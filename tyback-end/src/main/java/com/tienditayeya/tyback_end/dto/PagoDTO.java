package com.tienditayeya.tyback_end.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoDTO {
    private Integer idPagos;
    private String metodoPago;
    private BigDecimal monto;
    private LocalDateTime fechaPago;
    private Integer pedidoId;
}
