package com.tienditayeya.tyback_end.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "variantes_has_productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VariantesHP {

    @EmbeddedId
    private VariantesHPId id = new VariantesHPId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("variantesIdVariantes")
    @JoinColumn(name = "variantes_id_variantes")
    private Variantes variantes;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productosIdProductos")
    @JoinColumn(name = "productos_id_productos")
    private Producto producto;

    // Subclase requerida por JPA para llaves primarias compuestas
    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VariantesHPId implements Serializable {
        @Column(name = "variantes_id_variantes")
        private Long variantesIdVariantes;

        @Column(name = "productos_id_productos")
        private Long productosIdProductos;

        

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
    }

}
