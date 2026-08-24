package com.tienditayeya.tyback_end.controller;


import com.tienditayeya.tyback_end.dto.CarritoDTO;
import com.tienditayeya.tyback_end.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carritos")
@CrossOrigin(origins = "*") // Permite peticiones desde el frontend si lo necesitas
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    // Endpoint para listar todos: GET /api/carritos
    @GetMapping
    public ResponseEntity<List<CarritoDTO>> listarCarritos() {
        List<CarritoDTO> carritos = carritoService.obtenerTodos();
        return ResponseEntity.ok(carritos);
    }

    // Endpoint para buscar por ID: GET /api/carritos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CarritoDTO> obtenerCarritoPorId(@PathVariable int id) {
        try {
            CarritoDTO carrito = carritoService.obtenerPorId(id);
            return ResponseEntity.ok(carrito);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint para crear: POST /api/carritos
    @PostMapping
    public ResponseEntity<CarritoDTO> crearCarrito(@RequestBody CarritoDTO carritoDTO) {
        CarritoDTO nuevoCarrito = carritoService.guardar(carritoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCarrito);
    }

    // Endpoint para eliminar: DELETE /api/carritos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable int id) {
        try {
            carritoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}