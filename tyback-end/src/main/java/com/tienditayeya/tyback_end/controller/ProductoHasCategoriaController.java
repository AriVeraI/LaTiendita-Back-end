package com.tienditayeya.tyback_end.controller;

import com.tienditayeya.tyback_end.dto.ProductoHasCategoriaDTO;
import com.tienditayeya.tyback_end.service.ProductoHasCategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/producto-categoria")
public class ProductoHasCategoriaController {

    @Autowired
    private ProductoHasCategoriaService service;

    @GetMapping
    public List<ProductoHasCategoriaDTO> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{productoId}/{categoriaId}")
    public Optional<ProductoHasCategoriaDTO> buscarPorId(
            @PathVariable Integer productoId,
            @PathVariable Integer categoriaId) {
        return service.buscarPorId(productoId, categoriaId);
    }

    @PostMapping
    public ProductoHasCategoriaDTO guardar(@RequestBody ProductoHasCategoriaDTO dto) {
        return service.guardar(dto);
    }

    @DeleteMapping("/{productoId}/{categoriaId}")
    public void eliminar(
            @PathVariable Integer productoId,
            @PathVariable Integer categoriaId) {
        service.eliminar(productoId, categoriaId);
    }
}