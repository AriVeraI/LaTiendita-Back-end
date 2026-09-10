package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.AdminProductoRequest;
import com.tienditayeya.tyback_end.dto.CatalogProductoResponse;
import com.tienditayeya.tyback_end.model.ImagenProducto;
import com.tienditayeya.tyback_end.model.Producto;
import com.tienditayeya.tyback_end.model.ProductoHasCategoria;
import com.tienditayeya.tyback_end.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AdminProductoService {
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoHasCategoriaRepository relacionRepository;
    private final ImagenProductoRepository imagenRepository;
    private final CatalogService catalogService;

    public AdminProductoService(ProductoRepository productoRepository,
                                CategoriaRepository categoriaRepository,
                                ProductoHasCategoriaRepository relacionRepository,
                                ImagenProductoRepository imagenRepository,
                                CatalogService catalogService) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.relacionRepository = relacionRepository;
        this.imagenRepository = imagenRepository;
        this.catalogService = catalogService;
    }

    @Transactional
    public CatalogProductoResponse crear(AdminProductoRequest request) {
        categoriaRepository.findById(request.categoriaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoría no válida"));
        Producto p = new Producto();
        p.setSku(generarSku(request.tipoPieza()));
        aplicar(p, request);
        p = productoRepository.saveAndFlush(p);
        sincronizarRelaciones(p.getIdProductos(), request);
        return catalogService.obtener(p.getIdProductos());
    }

    @Transactional
    public CatalogProductoResponse actualizar(Integer id, AdminProductoRequest request) {
        categoriaRepository.findById(request.categoriaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoría no válida"));
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
        String prefijo = prefijo(request.tipoPieza());
        if (p.getSku() == null || !p.getSku().toUpperCase(Locale.ROOT).startsWith(prefijo)) {
            p.setSku(generarSku(request.tipoPieza()));
        }
        aplicar(p, request);
        productoRepository.saveAndFlush(p);
        sincronizarRelaciones(id, request);
        return catalogService.obtener(id);
    }

    private void aplicar(Producto p, AdminProductoRequest r) {
        p.setNombreProducto(r.nombreProducto().trim());
        p.setDescripcionProducto(r.descripcionProducto().trim());
        p.setPrecio(r.precio());
        p.setStock(r.stock());
        p.setDisponibilidad(r.stock() > 0 ? "disponible" : "agotado");
    }

    private void sincronizarRelaciones(Integer productoId, AdminProductoRequest r) {
        relacionRepository.deleteDirectByProductoId(productoId);
        relacionRepository.saveAndFlush(new ProductoHasCategoria(productoId, r.categoriaId()));
        imagenRepository.deleteDirectByProductoId(productoId.longValue());
        ImagenProducto imagen = new ImagenProducto(null, r.imagen().trim(), productoId.longValue());
        imagenRepository.saveAndFlush(imagen);
    }

    private String generarSku(String tipo) {
        String prefijo = prefijo(tipo);
        for (int i = 0; i < 30; i++) {
            String sku = prefijo + "-" + ThreadLocalRandom.current().nextInt(100000, 1000000);
            if (!productoRepository.existsBySkuIgnoreCase(sku)) return sku;
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No se pudo generar un SKU único");
    }

    private String prefijo(String tipo) {
        String t = tipo == null ? "" : tipo.trim().toLowerCase(Locale.ROOT);
        return switch (t) {
            case "anillos", "anillo" -> "ANI";
            case "collares", "collar" -> "COL";
            case "pulseras", "pulsera" -> "PUL";
            case "pendientes", "aretes", "pendiente", "arete" -> "ARE";
            default -> "JOY";
        };
    }
}
