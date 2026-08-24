package com.tienditayeya.tyback_end.dto;

import lombok.Data;
@Data
public class VariantesRequestDTO {
    private String skuVariantes;
    private String atributos;
    private String stockVariantes;

    public VariantesRequestDTO(){}

    public String getSkuVariantes() {
        return skuVariantes;
    }

    public void setSkuVariantes(String skuVariantes) {
        this.skuVariantes = skuVariantes;
    }

    public String getAtributos() {
        return atributos;
    }

    public void setAtributos(String atributos) {
        this.atributos = atributos;
    }

    public String getStockVariantes() {
        return stockVariantes;
    }

    public void setStockVariantes(String stockVariantes) {
        this.stockVariantes = stockVariantes;
    }
}
