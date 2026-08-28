package com.tienditayeya.tyback_end.repository;

import com.tienditayeya.tyback_end.model.ProductoHasCategoria;
import com.tienditayeya.tyback_end.model.ProductoHasCategoriaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoHasCategoriaRepository extends JpaRepository<ProductoHasCategoria, ProductoHasCategoriaId> {
    List<ProductoHasCategoria> findByProductosIdProductos(Integer productoId);
    long countByCategoriasIdCategoria(Integer categoriaId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from ProductoHasCategoria r where r.productosIdProductos = :productoId")
    int deleteDirectByProductoId(@Param("productoId") Integer productoId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from ProductoHasCategoria r where r.productosIdProductos = :productoId and r.categoriasIdCategoria = :categoriaId")
    int deleteDirect(@Param("productoId") Integer productoId, @Param("categoriaId") Integer categoriaId);
}
