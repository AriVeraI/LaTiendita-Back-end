package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.ActualizarCantidadDTO;
import com.tienditayeya.tyback_end.dto.CarritoDTO;
import com.tienditayeya.tyback_end.dto.CarritoDetalleDTO;
import com.tienditayeya.tyback_end.dto.ItemCarritoDTO;
import com.tienditayeya.tyback_end.model.Carrito;
import com.tienditayeya.tyback_end.model.CarritoProducto;
import com.tienditayeya.tyback_end.model.Producto;
import com.tienditayeya.tyback_end.repository.CarritoProductoRepository;
import com.tienditayeya.tyback_end.repository.CarritoRepository;
import com.tienditayeya.tyback_end.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private CarritoProductoRepository carritoProductoRepository;

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

    //Obtener o crear
    public Carrito obtenerOACrearCarritoPorUsuario(int idUsuario) {
        // Busca si el usuario ya cuenta con un carrito activo
        return carritoRepository.findByIdUsuario(idUsuario)
                .orElseGet(() -> {
                    // Si no existe, crea uno nuevo
                    Carrito nuevoCarrito = new Carrito();
                    nuevoCarrito.setIdUsuario(idUsuario);
                    nuevoCarrito.setFechaCracion(LocalDateTime.now());
                    nuevoCarrito.setFechaActualizacion(LocalDateTime.now());
                    return carritoRepository.save(nuevoCarrito);
                });
    }
    //
    @Autowired
    private ProductoRepository productoRepository; // Asegúrate de tener este repositorio inyectado

    public CarritoDetalleDTO obtenerCarritoDetalladoPorUsuario(int idUsuario) {
        // 1. Obtiene o crea el carrito del usuario
        Carrito carrito = obtenerOACrearCarritoPorUsuario(idUsuario);

        // 2. Busca los productos que pertenecen a este carrito
        List<CarritoProducto> productosEnCarrito = carritoProductoRepository.findByCarritoIdCarrito(carrito.getIdCarrito());

        // 3. Mapea la información hacia el DTO para el front-end
        CarritoDetalleDTO detalleDTO = new CarritoDetalleDTO();
        detalleDTO.setIdCarrito(carrito.getIdCarrito());

        List<ItemCarritoDTO> items = productosEnCarrito.stream().map(cp -> {
            ItemCarritoDTO item = new ItemCarritoDTO();
            item.setIdCarritoProductos(cp.getIdCarritoProductos());
            item.setCantidad(cp.getCantidad());
            item.setIdProducto(cp.getProductosIdProductos());

            // 4. Buscar la información del producto y asignarla al DTO del item
            Producto producto = productoRepository.findById(cp.getProductosIdProductos()).orElse(null);

            if (producto != null) {
                item.setNombreProducto(producto.getNombreProducto());
                item.setPrecioUnitario(producto.getPrecio());

            }

            return item;
        }).collect(Collectors.toList());

        detalleDTO.setItems(items);

        return detalleDTO;
    }

    public CarritoDetalleDTO actualizarCantidadProducto(ActualizarCantidadDTO actualizarCantidadDTO) {
        // 1. Buscar el Item a actualizar
        CarritoProducto carritoProducto = carritoProductoRepository.findById(actualizarCantidadDTO.getIdCarritoProductos())
                .orElseThrow(() -> new RuntimeException("No se encontró el producto en el carrito"));

        // 2. Actualizar la cantidad
        carritoProducto.setCantidad(actualizarCantidadDTO.getCantidad());

        // 3. Guardar los cambios en la base de datos
        carritoProductoRepository.save(carritoProducto);

        // 4. Obtener y retornar el DTO detallado actualizado del usuario
        // (Asumiendo que ya tienes un método que hace esto pasándole el idUsuario)
        return obtenerCarritoDetalladoPorUsuario(actualizarCantidadDTO.getIdUsuario());
    }

    public CarritoDetalleDTO eliminarItem(ActualizarCantidadDTO actualizarCantidadDTO) {
        // 1. Buscar el Item a actualizar
        CarritoProducto carritoProducto = carritoProductoRepository.findById(actualizarCantidadDTO.getIdCarritoProductos())
                .orElseThrow(() -> new RuntimeException("No se encontró el producto en el carrito"));

        // 3. Guardar los cambios en la base de datos
        carritoProductoRepository.delete(carritoProducto);

        // 4. Obtener y retornar el DTO detallado actualizado del usuario
        // (Asumiendo que ya tienes un método que hace esto pasándole el idUsuario)
        return obtenerCarritoDetalladoPorUsuario(actualizarCantidadDTO.getIdUsuario());
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