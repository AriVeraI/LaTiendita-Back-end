package com.tienditayeya.tyback_end.repository;

import com.tienditayeya.tyback_end.model.DetallePedido;
import com.tienditayeya.tyback_end.model.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoRepository {
    List<DetallePedido> findAll();

    DetallePedido save(DetallePedido detallePedido);

    void deleteById(Integer id);

}
