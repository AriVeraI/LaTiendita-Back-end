package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.VariantesHPRequestDTO;
import com.tienditayeya.tyback_end.model.Producto;
import com.tienditayeya.tyback_end.model.Variantes;
import com.tienditayeya.tyback_end.model.VariantesHP;
import com.tienditayeya.tyback_end.repository.VariantesHPRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class VariantesHPService {
    private final VariantesHPRepository repository;
    private final VariantesService variantesService;
    private final ProductoService productoService;

    public VariantesHPService(VariantesHPRepository repository, VariantesService variantesService,
                              ProductoService productoService) {
        this.repository = repository;
        this.variantesService = variantesService;
        this.productoService = productoService;
    }

    @Transactional(readOnly = true)
    public List<VariantesHP> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<VariantesHP> findByProductoId(Integer productoId) { // Integer
        return repository.findById_ProductosIdProductos(productoId);
    }

    @Transactional
    public VariantesHP create(VariantesHPRequestDTO dto) {
        Variantes variantes = variantesService.findById(dto.getVarianteId());

        // El servicio de producto regresa Optional<Producto>, usamos .orElseThrow para extraerlo de forma segura
        Producto producto = productoService.obtenerPorId(dto.getProductoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));

        VariantesHP relacion = new VariantesHP();
        relacion.setVariantes(variantes);
        relacion.setProducto(producto);
        relacion.getId().setVariantesIdVariantes(variantes.getIdVariantes());
        relacion.getId().setProductosIdProductos(producto.getIdProductos()); // Usando getIdProductos() que es Integer

        return repository.save(relacion);
    }

    @Transactional
    public void delete(Long variantesId, Integer productoId) { // Integer
        VariantesHP.VariantesHPId id = new VariantesHP.VariantesHPId(variantesId, productoId);
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Relación no encontrada");
        }
        repository.deleteById(id);
    }
}
