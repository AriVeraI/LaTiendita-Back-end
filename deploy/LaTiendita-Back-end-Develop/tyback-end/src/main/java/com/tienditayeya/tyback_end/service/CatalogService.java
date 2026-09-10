package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.CatalogProductoResponse;
import com.tienditayeya.tyback_end.model.Categoria;
import com.tienditayeya.tyback_end.model.ImagenProducto;
import com.tienditayeya.tyback_end.model.Producto;
import com.tienditayeya.tyback_end.model.ProductoHasCategoria;
import com.tienditayeya.tyback_end.repository.CategoriaRepository;
import com.tienditayeya.tyback_end.repository.ImagenProductoRepository;
import com.tienditayeya.tyback_end.repository.ProductoHasCategoriaRepository;
import com.tienditayeya.tyback_end.repository.ProductoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CatalogService {
    private final ProductoRepository productoRepository;
    private final ProductoHasCategoriaRepository relacionRepository;
    private final CategoriaRepository categoriaRepository;
    private final ImagenProductoRepository imagenRepository;

    public CatalogService(ProductoRepository productoRepository,
                          ProductoHasCategoriaRepository relacionRepository,
                          CategoriaRepository categoriaRepository,
                          ImagenProductoRepository imagenRepository) {
        this.productoRepository = productoRepository;
        this.relacionRepository = relacionRepository;
        this.categoriaRepository = categoriaRepository;
        this.imagenRepository = imagenRepository;
    }

    @Transactional(readOnly = true)
    public List<CatalogProductoResponse> listar() {
        return productoRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CatalogProductoResponse obtener(Integer id) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
        return toResponse(p);
    }

    private CatalogProductoResponse toResponse(Producto p) {
        List<Integer> idsCategorias = relacionRepository.findByProductosIdProductos(p.getIdProductos())
                .stream().map(ProductoHasCategoria::getCategoriasIdCategoria).toList();
        Map<Integer, Categoria> categoriasPorId = categoriaRepository.findAllById(idsCategorias).stream()
                .collect(Collectors.toMap(Categoria::getIdCategoria, c -> c));
        List<String> categorias = idsCategorias.stream()
                .map(categoriasPorId::get)
                .filter(Objects::nonNull)
                .map(Categoria::getNombreCategoria)
                .distinct()
                .toList();
        String imagen = imagenRepository.findFirstByProductosIdProductosOrderByIdImagenesProductosAsc(
                        p.getIdProductos().longValue())
                .map(ImagenProducto::getUrlImagen)
                .orElse("../../assets/Images/An1.png");
        return new CatalogProductoResponse(
                p.getIdProductos(), p.getSku(), p.getNombreProducto(), p.getDescripcionProducto(),
                p.getPrecio(), p.getStock(), p.getDisponibilidad(), categorias, imagen
        );
    }
}
