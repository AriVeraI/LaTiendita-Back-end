package com.tienditayeya.tyback_end.repository;
import com.tienditayeya.tyback_end.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
    // List<Carrito> findByIdUsuario(int idUsuario);
}