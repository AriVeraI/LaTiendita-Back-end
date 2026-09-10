package com.tienditayeya.tyback_end.repository;


import com.tienditayeya.tyback_end.model.CarritoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, Long> {
    // Metodo útil opcional para buscar los productos de un carrito específico
    List<CarritoProducto> findByCarritoIdCarrito(int carritoIdCarrito);
}