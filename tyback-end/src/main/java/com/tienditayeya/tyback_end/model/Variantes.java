package com.tienditayeya.tyback_end.model;

import jakarta.persistence.*;

@Entity
@Table(name = "variantes")
public class Variantes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_variantes")
    private Long idVariantes;

    @Column(name = "sku_variante")
    private String skuVariantes;

    @Column(name = "atributos")
    private String atributos;

    @Column(name = "stock_variante")
    private String stockVariantes;


    // ==========================================
    // CONSTRUCTOR VACÍO
    // ==========================================

    public Variantes() {
    }


    // ==========================================
    // GETTERS Y SETTERS
    // ==========================================

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
