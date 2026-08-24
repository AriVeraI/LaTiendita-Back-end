package com.tienditayeya.tyback_end.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "variantes_has_productos")
public class VariantesHP {

    @EmbeddedId
    private VariantesHPId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("variantesIdVariantes")
    @JoinColumn(name = "variantes_id_variantes")
    private Variantes variantes;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productosIdProductos")
    @JoinColumn(name = "productos_id_productos")
    private Producto producto;

    // Constructor vacío por defecto
    public VariantesHP() {
        this.id = new VariantesHPId();
    }

    // Constructor con parámetros
    public VariantesHP(VariantesHPId id, Variantes variantes, Producto producto) {
        this.id = id != null ? id : new VariantesHPId();
        this.variantes = variantes;
        this.producto = producto;
    }

    // Getters y Setters
    public VariantesHPId getId() {
        return id;
    }

    public void setId(VariantesHPId id) {
        this.id = id;
    }

    public Variantes getVariantes() {
        return variantes;
    }

    public void setVariantes(Variantes variantes) {
        this.variantes = variantes;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "VariantesHP{" +
                "id=" + id +
                ", variantes=" + variantes +
                ", producto=" + producto +
                '}';
    }

    // Subclase Embeddable para la Llave Compuesta
    @Embeddable
    public static class VariantesHPId implements Serializable {

        @Column(name = "variantes_id_variantes")
        private Long variantesIdVariantes;

        @Column(name = "productos_id_productos")
        private Integer productosIdProductos; // Integer porque Producto usa Integer en su ID

        public VariantesHPId() {
        }

        public VariantesHPId(Long variantesIdVariantes, Integer productosIdProductos) {
            this.variantesIdVariantes = variantesIdVariantes;
            this.productosIdProductos = productosIdProductos;
        }

        public Long getVariantesIdVariantes() {
            return variantesIdVariantes;
        }

        public void setVariantesIdVariantes(Long variantesIdVariantes) {
            this.variantesIdVariantes = variantesIdVariantes;
        }

        public Integer getProductosIdVariantes() { // Manteniendo consistencia por si acaso
            return productosIdProductos;
        }

        public Integer getProductosIdProductos() {
            return productosIdProductos;
        }

        public void setProductosIdProductos(Integer productosIdProductos) {
            this.productosIdProductos = productosIdProductos;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            VariantesHPId that = (VariantesHPId) o;
            return Objects.equals(variantesIdVariantes, that.variantesIdVariantes) &&
                    Objects.equals(productosIdProductos, that.productosIdProductos);
        }

        @Override
        public int hashCode() {
            return Objects.hash(variantesIdVariantes, productosIdProductos);
        }

        @Override
        public String toString() {
            return "VariantesHPId{" +
                    "variantesIdVariantes=" + variantesIdVariantes +
                    ", productosIdProductos=" + productosIdProductos +
                    '}';
        }
    }
}
