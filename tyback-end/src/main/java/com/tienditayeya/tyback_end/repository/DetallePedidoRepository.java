package com.tienditayeya.tyback_end.repository;

import com.tienditayeya.tyback_end.model.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {

    // Spring Data JPA ya te da findAll(), save(), findById(), deleteById(Long id) por defecto.
    // Si necesitas buscar por pedidoId, puedes agregarlo así:
    // List<DetallePedido> findByPedidoId(Long pedidoId);
}