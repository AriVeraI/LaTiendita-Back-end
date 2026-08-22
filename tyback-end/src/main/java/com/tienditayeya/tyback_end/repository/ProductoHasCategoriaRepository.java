package com.tienditayeya.tyback_end.repository;

import com.tienditayeya.tyback_end.model.ProductoHasCategoria;
import com.tienditayeya.tyback_end.model.ProductoHasCategoriaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoHasCategoriaRepository extends JpaRepository<ProductoHasCategoria, ProductoHasCategoriaId> {
}