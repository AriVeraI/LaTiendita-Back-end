package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.ProductoHasCategoriaDTO;
import com.tienditayeya.tyback_end.model.ProductoHasCategoria;
import com.tienditayeya.tyback_end.model.ProductoHasCategoriaId;
import com.tienditayeya.tyback_end.repository.ProductoHasCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoHasCategoriaService {

    @Autowired
    private ProductoHasCategoriaRepository repository;

    private ProductoHasCategoriaDTO convertirADto(ProductoHasCategoria entity) {
        return new ProductoHasCategoriaDTO(
                entity.getProductosIdProductos(),
                entity.getCategoriasIdCategoria()
        );
    }

    private ProductoHasCategoria convertirAEntidad(ProductoHasCategoriaDTO dto) {
        ProductoHasCategoria entity = new ProductoHasCategoria();
        entity.setProductosIdProductos(dto.getProductosIdProductos());
        entity.setCategoriasIdCategoria(dto.getCategoriasIdCategoria());
        return entity;
    }

    public List<ProductoHasCategoriaDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    public Optional<ProductoHasCategoriaDTO> buscarPorId(Integer productoId, Integer categoriaId) {
        ProductoHasCategoriaId idCompuesto = new ProductoHasCategoriaId(productoId, categoriaId);
        return repository.findById(idCompuesto)
                .map(this::convertirADto);
    }

    public ProductoHasCategoriaDTO guardar(ProductoHasCategoriaDTO dto) {
        ProductoHasCategoria entity = convertirAEntidad(dto);
        ProductoHasCategoria guardado = repository.save(entity);
        return convertirADto(guardado);
    }

    public void eliminar(Integer productoId, Integer categoriaId) {
        ProductoHasCategoriaId idCompuesto = new ProductoHasCategoriaId(productoId, categoriaId);
        repository.deleteById(idCompuesto);
    }
}