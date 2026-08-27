package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.DetallePedidoDTO;
import com.tienditayeya.tyback_end.model.DetallePedido;
import com.tienditayeya.tyback_end.repository.DetallePedidoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetallePedidoService {

    private final DetallePedidoRepository repository;

    public DetallePedidoService(DetallePedidoRepository repository) {
        this.repository = repository;
    }

    public List<DetallePedidoDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public DetallePedidoDTO findById(Long id) {
        DetallePedido detalle = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de pedido no encontrado con id: " + id));
        return toDTO(detalle);
    }

    public List<DetallePedidoDTO> findByPedidoId(Long pedidoId) {
        return repository.findByPedidosIdPedidos(pedidoId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public DetallePedidoDTO save(DetallePedidoDTO dto) {
        DetallePedido entity = toEntity(dto);
        DetallePedido saved = repository.save(entity);
        return toDTO(saved);
    }

    public DetallePedidoDTO update(Long id, DetallePedidoDTO dto) {
        DetallePedido existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de pedido no encontrado con id: " + id));

        existing.setCantidad(dto.getCantidad());
        existing.setPrecioTotalUnitario(dto.getPrecioTotalUnitario());
        existing.setPedidosIdPedidos(dto.getPedidosIdPedidos());
        existing.setProductosIdProductos(dto.getProductosIdProductos());

        return toDTO(repository.save(existing));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    // Conversiones Entity <-> DTO
    private DetallePedidoDTO toDTO(DetallePedido entity) {
        return DetallePedidoDTO.builder()
                .idDetallePedido(entity.getIdDetallePedido())
                .cantidad(entity.getCantidad())
                .precioTotalUnitario(entity.getPrecioTotalUnitario())
                .pedidosIdPedidos(entity.getPedidosIdPedidos())
                .productosIdProductos(entity.getProductosIdProductos())
                .build();
    }

    private DetallePedido toEntity(DetallePedidoDTO dto) {
        return DetallePedido.builder()
                .idDetallePedido(dto.getIdDetallePedido())
                .cantidad(dto.getCantidad())
                .precioTotalUnitario(dto.getPrecioTotalUnitario())
                .pedidosIdPedidos(dto.getPedidosIdPedidos())
                .productosIdProductos(dto.getProductosIdProductos())
                .build();
    }
}