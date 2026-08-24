package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.CarritoDTO;
import com.tienditayeya.tyback_end.model.Carrito;
import com.tienditayeya.tyback_end.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    // Obtener todos los carritos convertidos a DTO
    public List<CarritoDTO> obtenerTodos() {
        return carritoRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Obtener un carrito por ID
    public CarritoDTO obtenerPorId(int id) {
        Carrito carrito = carritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado con ID: " + id));
        return convertirADTO(carrito);
    }

    // Guardar o crear un carrito
    public CarritoDTO guardar(CarritoDTO carritoDTO) {
        Carrito carrito = convertirAEntidad(carritoDTO);

        // Asignar fechas automáticas si es necesario
        if (carrito.getFechaCracion() == null) {
            carrito.setFechaCracion(LocalDateTime.now());
        }
        carrito.setFechaActualizacion(LocalDateTime.now());

        Carrito guardado = carritoRepository.save(carrito);
        return convertirADTO(guardado);
    }

    // Eliminar un carrito
    public void eliminar(int id) {
        if (!carritoRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar, el carrito con ID " + id + " no existe.");
        }
        carritoRepository.deleteById(id);
    }

    // Métodos auxiliares de mapeo (Entidad <-> DTO)
    private CarritoDTO convertirADTO(Carrito carrito) {
        return new CarritoDTO(
                carrito.getIdCarrito(),
                carrito.getFechaCracion(),
                carrito.getFechaActualizacion(),
                carrito.getIdUsuario()
        );
    }

    private Carrito convertirAEntidad(CarritoDTO dto) {
        Carrito carrito = new Carrito();
        carrito.setIdCarrito(dto.getIdCarrito());
        carrito.setFechaCracion(dto.getFechaCreacion());
        carrito.setFechaActualizacion(dto.getFechaActualizacion());
        carrito.setIdUsuario(dto.getIdUsuario());
        return carrito;
    }
}