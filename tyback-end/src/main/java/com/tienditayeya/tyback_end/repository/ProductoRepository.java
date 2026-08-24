package com.tienditayeya.tyback_end.repository;


import com.tienditayeya.tyback_end.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
