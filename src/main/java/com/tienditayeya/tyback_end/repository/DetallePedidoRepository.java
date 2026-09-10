package com.tienditayeya.tyback_end.repository;

import com.tienditayeya.tyback_end.model.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {
    List<DetallePedido> findByPedidosIdPedidos(Long pedidoId);
    List<DetallePedido> findByProductosIdProductos(Integer productoId);
}
