package com.tienditayeya.tyback_end.controller;


import com.tienditayeya.tyback_end.dto.ImagenProductoDTO;
import com.tienditayeya.tyback_end.service.ImagenProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/imagenes-productos")
public class ImagenProductoController {

    @Autowired
    private ImagenProductoService imagenProductoService;

    @GetMapping
    public ResponseEntity<List<ImagenProductoDTO>> listarTodas() {
        return ResponseEntity.ok(imagenProductoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImagenProductoDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(imagenProductoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<ImagenProductoDTO> crear(@RequestBody ImagenProductoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(imagenProductoService.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImagenProductoDTO> actualizar(@PathVariable Long id, @RequestBody ImagenProductoDTO dto) {
        return ResponseEntity.ok(imagenProductoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        imagenProductoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}