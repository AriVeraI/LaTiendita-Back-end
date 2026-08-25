package com.tienditayeya.tyback_end.repository;


import com.tienditayeya.tyback_end.model.ImagenProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenProductoRepository extends JpaRepository<ImagenProducto, Long> {
}
