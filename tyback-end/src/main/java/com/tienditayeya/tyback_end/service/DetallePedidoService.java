package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.DetallePedidoDTO;
import com.tienditayeya.tyback_end.model.DetallePedido;
import com.tienditayeya.tyback_end.repository.DetallePedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;

    public DetallePedidoService(DetallePedidoRepository detallePedidoRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
    }

    public List<DetallePedidoDTO> findAll() {
        return detallePedidoRepository.findAll().stream().map(this::convertToDTO).toList();
    }

    public DetallePedidoDTO findById(Long id) {
        return detallePedidoRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Detalle de pedido no encontrado con ID: " + id));
    }

    public List<DetallePedidoDTO> findByPedidoId(Long pedidoId) {
        return detallePedidoRepository.findByPedidosIdPedidos(pedidoId).stream()
                .map(this::convertToDTO)
                .toList();
    }

    public DetallePedidoDTO save(DetallePedidoDTO dto) {
        return convertToDTO(detallePedidoRepository.save(convertToEntity(dto)));
    }

    public DetallePedidoDTO update(Long id, DetallePedidoDTO dto) {
        DetallePedido existente = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de pedido no encontrado para actualizar con ID: " + id));

        existente.setCantidad(dto.getCantidad());
        existente.setPrecioTotalUnitario(dto.getPrecioTotalUnitario());
        existente.setPedidosIdPedidos(dto.getPedidosIdPedidos());
        existente.setProductosIdProductos(dto.getProductosIdProductos() == null ? null : dto.getProductosIdProductos().intValue());

        return convertToDTO(detallePedidoRepository.save(existente));
    }

    public void deleteById(Long id) {
        detallePedidoRepository.deleteById(id);
    }

    private DetallePedidoDTO convertToDTO(DetallePedido detalle) {
        DetallePedidoDTO dto = new DetallePedidoDTO();
        dto.setIdDetallePedido(detalle.getIdDetallePedido());
        dto.setCantidad(detalle.getCantidad());
        dto.setPrecioTotalUnitario(detalle.getPrecioTotalUnitario());
        dto.setPedidosIdPedidos(detalle.getPedidosIdPedidos());
        dto.setProductosIdProductos(detalle.getProductosIdProductos() == null ? null : detalle.getProductosIdProductos().longValue());
        return dto;
    }

    private DetallePedido convertToEntity(DetallePedidoDTO dto) {
        DetallePedido detalle = new DetallePedido();
        detalle.setIdDetallePedido(dto.getIdDetallePedido());
        detalle.setCantidad(dto.getCantidad());
        detalle.setPrecioTotalUnitario(dto.getPrecioTotalUnitario());
        detalle.setPedidosIdPedidos(dto.getPedidosIdPedidos());
        detalle.setProductosIdProductos(dto.getProductosIdProductos() == null ? null : dto.getProductosIdProductos().intValue());
        return detalle;
    }
}
