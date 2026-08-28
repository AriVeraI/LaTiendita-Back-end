package com.tienditayeya.tyback_end.repository;

import com.tienditayeya.tyback_end.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    boolean existsByNumeroPedido(Integer numeroPedido);
}
