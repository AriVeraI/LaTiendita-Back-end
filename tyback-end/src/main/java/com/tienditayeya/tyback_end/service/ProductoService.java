package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.model.Producto;
import com.tienditayeya.tyback_end.repository.DetallePedidoRepository;
import com.tienditayeya.tyback_end.repository.ImagenProductoRepository;
import com.tienditayeya.tyback_end.repository.ProductoHasCategoriaRepository;
import com.tienditayeya.tyback_end.repository.ProductoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final ProductoHasCategoriaRepository categoriaRelacionRepository;
    private final ImagenProductoRepository imagenRepository;
    private final DetallePedidoRepository detallePedidoRepository;

    public ProductoService(ProductoRepository productoRepository,
                           ProductoHasCategoriaRepository categoriaRelacionRepository,
                           ImagenProductoRepository imagenRepository,
                           DetallePedidoRepository detallePedidoRepository){
        this.productoRepository = productoRepository;
        this.categoriaRelacionRepository = categoriaRelacionRepository;
        this.imagenRepository = imagenRepository;
        this.detallePedidoRepository = detallePedidoRepository;
    }

    public List<Producto> obtenerTodos(){ return productoRepository.findAll(); }
    public Optional<Producto> obtenerPorId(Integer id){ return productoRepository.findById(id); }

    public Producto guardar(Producto producto) {
        validar(producto);
        return productoRepository.save(producto);
    }

    public Producto actualizar(Producto producto) {
        validar(producto);
        if (producto.getIdProductos() == null || !productoRepository.existsById(producto.getIdProductos())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        return productoRepository.save(producto);
    }

    @Transactional
    public void eliminar(Integer id) {
        if (!productoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        if (!detallePedidoRepository.findByProductosIdProductos(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "No se puede eliminar un producto con historial de pedidos; cambia su stock a 0 para retirarlo del catálogo");
        }
        categoriaRelacionRepository.deleteDirectByProductoId(id);
        imagenRepository.deleteDirectByProductoId(id.longValue());
        productoRepository.deleteById(id);
    }

    private void validar(Producto p) {
        if (p.getPrecio() == null || p.getPrecio().signum() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El precio debe ser mayor a cero");
        }
        if (p.getStock() == null || p.getStock() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El stock no puede ser negativo");
        }
        p.setDisponibilidad(p.getStock() == 0 ? "agotado" : "disponible");
    }
}
