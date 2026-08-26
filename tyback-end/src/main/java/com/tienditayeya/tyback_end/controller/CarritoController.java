package com.tienditayeya.tyback_end.controller;


import com.tienditayeya.tyback_end.dto.ActualizarCantidadDTO;
import com.tienditayeya.tyback_end.dto.CarritoDTO;
import com.tienditayeya.tyback_end.dto.CarritoDetalleDTO;
import com.tienditayeya.tyback_end.model.Carrito;
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

    //
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<CarritoDetalleDTO> obtenerCarritoPorUsuario(@PathVariable int idUsuario) {
        CarritoDetalleDTO carritoDetalle = carritoService.obtenerCarritoDetalladoPorUsuario(idUsuario);
        return ResponseEntity.ok(carritoDetalle);
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

    //
    @GetMapping("/usuario/carrito/{id}")
    public ResponseEntity<Carrito> obtenerCarritoDelUsuario(@PathVariable int id) {
        try {
            Carrito carrito = carritoService.obtenerOACrearCarritoPorUsuario(id);
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

    // -------Funcionalidades tienda
    @GetMapping("/detalle/{id}")
    public ResponseEntity<CarritoDetalleDTO> obtenerDetalleDeCarrito(@PathVariable int id) {
        try {
            CarritoDetalleDTO carrito = carritoService.obtenerCarritoDetalladoPorUsuario(id);
            return ResponseEntity.ok(carrito);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/actualizar")
    public ResponseEntity<CarritoDetalleDTO> actualizarCantidad(@RequestBody ActualizarCantidadDTO actualizarCantidadDTO) {
        try {
            CarritoDetalleDTO carrito = carritoService.actualizarCantidadProducto(actualizarCantidadDTO);
            return ResponseEntity.ok(carrito);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/borrar-item")
    public ResponseEntity<CarritoDetalleDTO> eliminarItem(@RequestBody ActualizarCantidadDTO actualizarCantidadDTO) {
        try {
            CarritoDetalleDTO carrito = carritoService.eliminarItem(actualizarCantidadDTO);
            return ResponseEntity.ok(carrito);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}