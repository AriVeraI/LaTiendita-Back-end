package com.tienditayeya.tyback_end.controller;

import com.tienditayeya.tyback_end.dto.CategoriaDTO;
import com.tienditayeya.tyback_end.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaDTO> listar() {
        return categoriaService.listarCategorias();
    }

    @GetMapping("/{id}")
    public Optional<CategoriaDTO> buscarPorId(@PathVariable Integer id) {
        return categoriaService.buscarPorId(id);
    }

    @PostMapping
    public CategoriaDTO guardar(@RequestBody CategoriaDTO categoriaDto) {
        return categoriaService.guardarCategoria(categoriaDto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        categoriaService.eliminarCategoria(id);
    }
}