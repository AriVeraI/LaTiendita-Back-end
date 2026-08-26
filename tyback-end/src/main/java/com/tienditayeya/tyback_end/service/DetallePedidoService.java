package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.DetallePedidoDTO;
import com.tienditayeya.tyback_end.model.DetallePedido;
import com.tienditayeya.tyback_end.repository.DetallePedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;

    public DetallePedidoService(DetallePedidoRepository detallePedidoRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
    }

    // Método para listar todos (mapeando de Entidad a DTO)
    public List<DetallePedidoDTO> findAll() {
        return detallePedidoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Buscar por ID
    public DetallePedidoDTO findById(Long id) {
        DetallePedido detalle = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de pedido no encontrado con ID: " + id));
        return convertToDTO(detalle);
    }

    // Buscar por ID de pedido
    public List<DetallePedidoDTO> findByPedidoId(Long pedidoId) {
        // Si tu repositorio tiene el método findByPedidoId, úsalo aquí.
        // Si no, puedes retornar una lista filtrada o adaptarla según tu base de datos.
        return detallePedidoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Guardar un nuevo registro
    public DetallePedidoDTO save(DetallePedidoDTO dto) {
        DetallePedido detalle = convertToEntity(dto);
        DetallePedido saved = detallePedidoRepository.save(detalle);
        return convertToDTO(saved);
    }

    // Actualizar registro existente
    public DetallePedidoDTO update(Long id, DetallePedidoDTO dto) {
        DetallePedido existente = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de pedido no encontrado para actualizar con ID: " + id));

        // Aquí actualizas los campos necesarios según tu entidad
        // existente.setCantidad(dto.getCantidad());

        DetallePedido updated = detallePedidoRepository.save(existente);
        return convertToDTO(updated);
    }

    // Eliminar por ID
    public void deleteById(Long id) {
        detallePedidoRepository.deleteById(id);
    }

    // Métodos auxiliares de conversión (Entity <-> DTO)
    private DetallePedidoDTO convertToDTO(DetallePedido detalle) {
        DetallePedidoDTO dto = new DetallePedidoDTO();
        // Asigna los campos de detalle a dto según los getters/setters que tengas
        return dto;
    }

    private DetallePedido convertToEntity(DetallePedidoDTO dto) {
        DetallePedido detalle = new DetallePedido();
        // Asigna los campos de dto a detalle según los getters/setters que tengas
        return detalle;
    }
}