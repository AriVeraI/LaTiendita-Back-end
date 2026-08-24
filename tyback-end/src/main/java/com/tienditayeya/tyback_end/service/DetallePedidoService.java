package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.model.DetallePedido;
import com.tienditayeya.tyback_end.model.Producto;
import com.tienditayeya.tyback_end.repository.DetallePedidoRepository;
import com.tienditayeya.tyback_end.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetallePedidoService {
    private final DetallePedidoRepository detallePedidoRepository;

    public DetallePedidoService(DetallePedidoRepository detallePedidoRepository){
        this.detallePedidoRepository = detallePedidoRepository;
    }

    //como hacer un select * from productos
    public List<DetallePedido> obtenerTodos(){
        return detallePedidoRepository.findAll();
    }

    public List<DetallePedido> obtenerDetalle() {
        return detallePedidoRepository.findAll();
    }
    //se utiliza para crear y actualizar, revisa el id para saber qué ejecutar
    public DetallePedido guardar(DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }

    public DetallePedido actualizar(DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }
    public void eliminar(Integer id) {
        detallePedidoRepository.deleteById(id);
    }


}
