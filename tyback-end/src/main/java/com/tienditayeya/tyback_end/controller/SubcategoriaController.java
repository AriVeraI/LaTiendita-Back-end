package com.tienditayeya.tyback_end.controller;

import com.tienditayeya.tyback_end.dto.SubcategoriaDTO;
import com.tienditayeya.tyback_end.service.SubcategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subcategorias")
public class SubcategoriaController {

    @Autowired
    private SubcategoriaService subcategoriaService;

    @GetMapping
    public List<SubcategoriaDTO> listar() {
        return subcategoriaService.listarSubcategorias();
    }

    @GetMapping("/{id}")
    public Optional<SubcategoriaDTO> buscarPorId(@PathVariable Integer id) {
        return subcategoriaService.buscarPorId(id);
    }

    @PostMapping
    public SubcategoriaDTO guardar(@RequestBody SubcategoriaDTO subcategoriaDto) {
        return subcategoriaService.guardarSubcategoria(subcategoriaDto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        subcategoriaService.eliminarSubcategoria(id);
    }
}