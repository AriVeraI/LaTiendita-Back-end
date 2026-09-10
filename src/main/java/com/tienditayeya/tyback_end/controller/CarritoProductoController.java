package com.tienditayeya.tyback_end.controller;

import com.tienditayeya.tyback_end.dto.CarritoProductoDTO;
import com.tienditayeya.tyback_end.service.AuthSessionService;
import com.tienditayeya.tyback_end.service.CarritoProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/carrito-productos")
public class CarritoProductoController {
    private final CarritoProductoService service;
    private final AuthSessionService auth;
    public CarritoProductoController(CarritoProductoService service,AuthSessionService auth){this.service=service;this.auth=auth;}
    @GetMapping public ResponseEntity<List<CarritoProductoDTO>> all(@RequestHeader(value="X-Session-Token",required=false)String t){auth.requerirAdmin(t);return ResponseEntity.ok(service.obtenerTodos());}
    @GetMapping("/{id}") public ResponseEntity<CarritoProductoDTO> one(@RequestHeader(value="X-Session-Token",required=false)String t,@PathVariable Long id){auth.requerirAdmin(t);return ResponseEntity.ok(service.obtenerPorId(id));}
    @PostMapping public ResponseEntity<CarritoProductoDTO> create(@RequestHeader(value="X-Session-Token",required=false)String t,@RequestBody CarritoProductoDTO d){auth.requerirAdmin(t);return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(d));}
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@RequestHeader(value="X-Session-Token",required=false)String t,@PathVariable Long id){auth.requerirAdmin(t);service.eliminar(id);return ResponseEntity.noContent().build();}
}
