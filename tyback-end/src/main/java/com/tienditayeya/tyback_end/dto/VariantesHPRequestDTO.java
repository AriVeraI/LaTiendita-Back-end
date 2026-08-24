package com.tienditayeya.tyback_end.dto;

import lombok.Data;

@Data
public class VariantesHPRequestDTO {
    private Long varianteId;
    private Integer productoId;

    public VariantesHPRequestDTO() {
    }

    public VariantesHPRequestDTO(Long varianteId, Integer productoId) {
        this.varianteId = varianteId;
        this.productoId = productoId;
    }

    public Long getVarianteId() {
        return varianteId;
    }

    public void setVarianteId(Long varianteId) {
        this.varianteId = varianteId;
    }

    public Integer getProductoId() { // <-- Debe regresar Integer
        return productoId;
    }

    public void setProductoId(Integer productoId) { // <-- Debe recibir Integer
        this.productoId = productoId;
    }

    @Override
    public String toString() {
        return "VariantesHPRequestDTO{" +
                "varianteId=" + varianteId +
                ", productoId=" + productoId +
                '}';
    }
}
