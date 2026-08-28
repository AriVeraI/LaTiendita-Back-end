package com.tienditayeya.tyback_end.controller;

import com.tienditayeya.tyback_end.dto.SubcategoriaDTO;
import com.tienditayeya.tyback_end.service.SubcategoriaService;
import com.tienditayeya.tyback_end.service.AuthSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subcategorias")
public class SubcategoriaController {

    @Autowired
    private SubcategoriaService subcategoriaService;
    @Autowired
    private AuthSessionService authSessionService;

    @GetMapping
    public List<SubcategoriaDTO> listar() {
        return subcategoriaService.listarSubcategorias();
    }

    @GetMapping("/{id}")
    public Optional<SubcategoriaDTO> buscarPorId(@PathVariable Integer id) {
        return subcategoriaService.buscarPorId(id);
    }

    @PostMapping
    public SubcategoriaDTO guardar(@RequestHeader(value="X-Session-Token", required=false) String token, @RequestBody SubcategoriaDTO subcategoriaDto) {
        authSessionService.requerirAdmin(token);
        return subcategoriaService.guardarSubcategoria(subcategoriaDto);
    }

    @PutMapping("/{id}")
    public SubcategoriaDTO actualizar(@RequestHeader(value="X-Session-Token", required=false) String token, @PathVariable Integer id, @RequestBody SubcategoriaDTO dto) {
        authSessionService.requerirAdmin(token);
        dto.setIdSubcategoria(id);
        return subcategoriaService.guardarSubcategoria(dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@RequestHeader(value="X-Session-Token", required=false) String token, @PathVariable Integer id) {
        authSessionService.requerirAdmin(token);
        subcategoriaService.eliminarSubcategoria(id);
    }
}