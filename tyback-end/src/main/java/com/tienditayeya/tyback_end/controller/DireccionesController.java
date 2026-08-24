package com.tienditayeya.tyback_end.controller;


import com.tienditayeya.tyback_end.dto.DireccionesDTO;
import com.tienditayeya.tyback_end.service.DireccionesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/direcciones")
public class DireccionesController {

    private final DireccionesService direccionService;

    public DireccionesController(DireccionesService direccionService) {
        this.direccionService = direccionService;
    }

    @GetMapping
    public ResponseEntity<List<DireccionesDTO>> listarTodas() {
        return ResponseEntity.ok(direccionService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DireccionesDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(direccionService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<DireccionesDTO> crear(@Valid @RequestBody DireccionesDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(direccionService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DireccionesDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody DireccionesDTO dto) {
        return ResponseEntity.ok(direccionService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        direccionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}