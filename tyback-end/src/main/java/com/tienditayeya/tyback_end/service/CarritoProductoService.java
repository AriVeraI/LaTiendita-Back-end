package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.CarritoProductoDTO;
import com.tienditayeya.tyback_end.model.CarritoProducto;
import com.tienditayeya.tyback_end.repository.CarritoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarritoProductoService {

    @Autowired
    private CarritoProductoRepository repository;

    public List<CarritoProductoDTO> obtenerTodos() {
        return repository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public CarritoProductoDTO obtenerPorId(Long id) {
        CarritoProducto entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado con ID: " + id));
        return convertirADTO(entity);
    }

    public CarritoProductoDTO guardar(CarritoProductoDTO dto) {
        CarritoProducto entity = convertirAEntidad(dto);
        CarritoProducto guardado = repository.save(entity);
        return convertirADTO(guardado);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar, el registro con ID " + id + " no existe.");
        }
        repository.deleteById(id);
    }

    // Mappers
    private CarritoProductoDTO convertirADTO(CarritoProducto entity) {
        return new CarritoProductoDTO(
                entity.getIdCarritoProductos(),
                entity.getCantidad(),
                entity.getProductosIdProductos(),
                entity.getCarritoIdCarrito()
        );
    }

    private CarritoProducto convertirAEntidad(CarritoProductoDTO dto) {
        CarritoProducto entity = new CarritoProducto();
        entity.setIdCarritoProductos(dto.getIdCarritoProductos());
        entity.setCantidad(dto.getCantidad());
        entity.setProductosIdProductos(dto.getProductosIdProductos());
        entity.setCarritoIdCarrito(dto.getCarritoIdCarrito());
        return entity;
    }
}
