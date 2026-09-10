package com.tienditayeya.tyback_end.repository;

import com.tienditayeya.tyback_end.model.ImagenProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ImagenProductoRepository extends JpaRepository<ImagenProducto, Long> {
    List<ImagenProducto> findByProductosIdProductos(Long productoId);
    Optional<ImagenProducto> findFirstByProductosIdProductosOrderByIdImagenesProductosAsc(Long productoId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from ImagenProducto i where i.productosIdProductos = :productoId")
    int deleteDirectByProductoId(@Param("productoId") Long productoId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from ImagenProducto i where i.idImagenesProductos = :id")
    int deleteDirectById(@Param("id") Long id);
}
