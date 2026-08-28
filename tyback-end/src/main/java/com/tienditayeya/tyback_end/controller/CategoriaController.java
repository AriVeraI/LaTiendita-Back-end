package com.tienditayeya.tyback_end.controller;

import com.tienditayeya.tyback_end.dto.CategoriaDTO;
import com.tienditayeya.tyback_end.service.CategoriaService;
import com.tienditayeya.tyback_end.service.AuthSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;
    private final AuthSessionService authSessionService;

    public CategoriaController(CategoriaService categoriaService, AuthSessionService authSessionService) {
        this.categoriaService = categoriaService;
        this.authSessionService = authSessionService;
    }

    @GetMapping public List<CategoriaDTO> listar() { return categoriaService.listarCategorias(); }
    @GetMapping("/{id}") public Optional<CategoriaDTO> buscarPorId(@PathVariable Integer id) { return categoriaService.buscarPorId(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaDTO guardar(@RequestHeader(value="X-Session-Token", required=false) String token, @RequestBody CategoriaDTO dto) {
        authSessionService.requerirAdmin(token);
        return categoriaService.guardarCategoria(dto);
    }

    @PutMapping("/{id}")
    public CategoriaDTO actualizar(@RequestHeader(value="X-Session-Token", required=false) String token,
                                   @PathVariable Integer id, @RequestBody CategoriaDTO dto) {
        authSessionService.requerirAdmin(token);
        dto.setIdCategoria(id);
        return categoriaService.guardarCategoria(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@RequestHeader(value="X-Session-Token", required=false) String token,
                                         @PathVariable Integer id) {
        authSessionService.requerirAdmin(token);
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
