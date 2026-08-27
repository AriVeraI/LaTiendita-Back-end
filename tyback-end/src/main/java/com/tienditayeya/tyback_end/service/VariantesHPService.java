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

@Service
public class VariantesHPService {

    private final VariantesHPRepository repository;
    private final VariantesService variantesService;
    private final ProductoService productoService;


    public VariantesHPService(
            VariantesHPRepository repository,
            VariantesService variantesService,
            ProductoService productoService) {

        this.repository = repository;
        this.variantesService = variantesService;
        this.productoService = productoService;
    }


    // ======================================================
    // OBTENER TODAS LAS RELACIONES
    // ======================================================

    @Transactional(readOnly = true)
    public List<VariantesHP> findAll() {

        return repository.findAll();
    }


    // ======================================================
    // OBTENER VARIANTES DE UN PRODUCTO
    // ======================================================

    @Transactional(readOnly = true)
    public List<VariantesHP> findByProductoId(Integer productoId) {

        return repository.findById_ProductosIdProductos(productoId);
    }


    // ======================================================
    // CREAR RELACIÓN VARIANTE - PRODUCTO
    // ======================================================

    @Transactional
    public VariantesHP create(VariantesHPRequestDTO dto) {

        // Buscar variante
        Variantes variante =
                variantesService.findById(dto.getVarianteId());


        // Buscar producto como ENTIDAD
        Producto producto =
                productoService
                        .obtenerEntidadPorId(dto.getProductoId())
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Producto no encontrado"
                                )
                        );


        // Crear relación
        VariantesHP relacion = new VariantesHP();

        relacion.setVariantes(variante);

        relacion.setProducto(producto);


        // Establecer IDs de la llave compuesta
        relacion
                .getId()
                .setVariantesIdVariantes(
                        variante.getIdVariantes()
                );

        relacion
                .getId()
                .setProductosIdProductos(
                        producto.getIdProductos()
                );


        return repository.save(relacion);
    }


    // ======================================================
    // ELIMINAR RELACIÓN
    // ======================================================

    @Transactional
    public void delete(
            Long variantesId,
            Integer productoId) {


        VariantesHP.VariantesHPId id =
                new VariantesHP.VariantesHPId(
                        variantesId,
                        productoId
                );


        if (!repository.existsById(id)) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Relación no encontrada"
            );
        }


        repository.deleteById(id);
    }
}
