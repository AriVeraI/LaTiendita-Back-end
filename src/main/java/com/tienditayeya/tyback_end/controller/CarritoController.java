package com.tienditayeya.tyback_end.controller;

import com.tienditayeya.tyback_end.dto.CarritoDTO;
import com.tienditayeya.tyback_end.dto.CarritoDetalleDTO;
import com.tienditayeya.tyback_end.model.Carrito;
import com.tienditayeya.tyback_end.service.AuthSessionService;
import com.tienditayeya.tyback_end.service.CarritoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/carritos")
public class CarritoController {
    private final CarritoService carritoService;
    private final AuthSessionService auth;
    public CarritoController(CarritoService carritoService, AuthSessionService auth){this.carritoService=carritoService;this.auth=auth;}

    // El frontend actual usa localStorage; estos endpoints quedan como CRUD administrativo protegido.
    @GetMapping public ResponseEntity<List<CarritoDTO>> listar(@RequestHeader(value="X-Session-Token",required=false)String t){auth.requerirAdmin(t);return ResponseEntity.ok(carritoService.obtenerTodos());}
    @GetMapping("/usuario/{idUsuario}") public ResponseEntity<CarritoDetalleDTO> porUsuario(@RequestHeader(value="X-Session-Token",required=false)String t,@PathVariable int idUsuario){auth.requerirAdmin(t);return ResponseEntity.ok(carritoService.obtenerCarritoDetalladoPorUsuario(idUsuario));}
    @GetMapping("/{id}") public ResponseEntity<CarritoDTO> porId(@RequestHeader(value="X-Session-Token",required=false)String t,@PathVariable int id){auth.requerirAdmin(t);return ResponseEntity.ok(carritoService.obtenerPorId(id));}
    @GetMapping("usuario/carrito/{id}") public ResponseEntity<Carrito> carritoUsuario(@RequestHeader(value="X-Session-Token",required=false)String t,@PathVariable int id){auth.requerirAdmin(t);return ResponseEntity.ok(carritoService.obtenerOACrearCarritoPorUsuario(id));}
    @GetMapping("usuario/detalle/{id}") public ResponseEntity<CarritoDetalleDTO> detalle(@RequestHeader(value="X-Session-Token",required=false)String t,@PathVariable int id){auth.requerirAdmin(t);return ResponseEntity.ok(carritoService.obtenerCarritoDetalladoPorUsuario(id));}
    @PostMapping public ResponseEntity<CarritoDTO> crear(@RequestHeader(value="X-Session-Token",required=false)String t,@RequestBody CarritoDTO d){auth.requerirAdmin(t);return ResponseEntity.status(HttpStatus.CREATED).body(carritoService.guardar(d));}
    @DeleteMapping("/{id}") public ResponseEntity<Void> eliminar(@RequestHeader(value="X-Session-Token",required=false)String t,@PathVariable int id){auth.requerirAdmin(t);carritoService.eliminar(id);return ResponseEntity.noContent().build();}
}
