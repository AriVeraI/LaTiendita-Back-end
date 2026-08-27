package com.tienditayeya.tyback_end.controller;

import com.tienditayeya.tyback_end.dto.ProductoDTO;
import com.tienditayeya.tyback_end.model.Producto;
import com.tienditayeya.tyback_end.service.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {

        this.productoService = productoService;
    }


    // ==========================================================
    // OBTENER TODOS
    // ==========================================================

    @GetMapping
    public List<ProductoDTO> obtenerTodos() {

        return productoService.obtenerTodos();
    }


    // ==========================================================
    // OBTENER UNO
    // ==========================================================

    @GetMapping("/{id}")
    public Optional<ProductoDTO> obtenerPorId(
            @PathVariable Integer id) {

        return productoService.obtenerPorId(id);
    }


    // ==========================================================
    // CREAR
    // ==========================================================

    @PostMapping
    public Producto guardar(
            @RequestBody Producto producto) {

        return productoService.guardar(producto);
    }


    // ==========================================================
    // ACTUALIZAR
    // ==========================================================

    @PutMapping("/{id}")
    public Producto actualizar(
            @PathVariable Integer id,
            @RequestBody Producto producto) {

        producto.setIdProductos(id);

        return productoService.actualizar(producto);
    }


    // ==========================================================
    // ELIMINAR
    // ==========================================================

    @DeleteMapping("/{id}")
    public void eliminar(
            @PathVariable Integer id) {

        productoService.eliminar(id);
    }
}