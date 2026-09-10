package com.tienditayeya.tyback_end.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "variantes")
@Data
public class Variantes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_variantes")
    private Long idVariantes;

    @Column(name = "sku_variante", nullable = false, length = 45)
    private String skuVariantes;

    @Column(name = "atributos", nullable = false, length = 45)
    private String atributos;

    @Column(name = "stock_variante", nullable = false, length = 45)
    private String stockVariantes;

    public Variantes(){}

    public Variantes(Long idVariantes, String skuVariantes, String atributos, String stockVariantes) {
        this.idVariantes = idVariantes;
        this.skuVariantes = skuVariantes;
        this.atributos = atributos;
        this.stockVariantes = stockVariantes;
    }

    public Long getIdVariantes() {
        return idVariantes;
    }

    public void setIdVariantes(Long idVariantes) {
        this.idVariantes = idVariantes;
    }

    public String getSkuVariantes() {
        return skuVariantes;
    }

    public void setSkuVariantes(String skuVariantes) {
        this.skuVariantes = skuVariantes;
    }

    public String getStockVariantes() {
        return stockVariantes;
    }

    public void setStockVariantes(String stockVariantes) {
        this.stockVariantes = stockVariantes;
    }

    public String getAtributos() {
        return atributos;
    }

    public void setAtributos(String atributos) {
        this.atributos = atributos;
    }

    @Override
    public String toString() {
        return "Variantes{" +
                "idVariantes=" + idVariantes +
                ", skuVariantes='" + skuVariantes + '\'' +
                ", atributos='" + atributos + '\'' +
                ", stockVariantes='" + stockVariantes + '\'' +
                '}';
    }
}
