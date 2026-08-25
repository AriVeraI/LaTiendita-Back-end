package com.tienditayeya.tyback_end.controller;

import com.tienditayeya.tyback_end.dto.CarritoProductoDTO;
import com.tienditayeya.tyback_end.service.CarritoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito-productos")
@CrossOrigin(origins = "*")
public class CarritoProductoController {

    @Autowired
    private CarritoProductoService service;

    @GetMapping
    public ResponseEntity<List<CarritoProductoDTO>> listarTodos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarritoProductoDTO> obtenerPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.obtenerPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<CarritoProductoDTO> crear(@RequestBody CarritoProductoDTO dto) {
        CarritoProductoDTO nuevo = service.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}