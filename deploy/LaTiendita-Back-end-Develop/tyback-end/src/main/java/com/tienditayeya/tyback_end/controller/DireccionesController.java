package com.tienditayeya.tyback_end.controller;
import com.tienditayeya.tyback_end.dto.DireccionesDTO;
import com.tienditayeya.tyback_end.service.*;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/direcciones")
public class DireccionesController{
 private final DireccionesService service; private final AuthSessionService auth;
 public DireccionesController(DireccionesService service,AuthSessionService auth){this.service=service;this.auth=auth;}
 @GetMapping public ResponseEntity<List<DireccionesDTO>> all(@RequestHeader(value="X-Session-Token",required=false)String t){auth.requerirAdmin(t);return ResponseEntity.ok(service.listarTodas());}
 @GetMapping("/{id}") public ResponseEntity<DireccionesDTO> one(@RequestHeader(value="X-Session-Token",required=false)String t,@PathVariable Long id){auth.requerirAdmin(t);return ResponseEntity.ok(service.obtenerPorId(id));}
 @PostMapping public ResponseEntity<DireccionesDTO> create(@RequestHeader(value="X-Session-Token",required=false)String t,@Valid @RequestBody DireccionesDTO d){auth.requerirAdmin(t);return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(d));}
 @PutMapping("/{id}") public ResponseEntity<DireccionesDTO> update(@RequestHeader(value="X-Session-Token",required=false)String t,@PathVariable Long id,@Valid @RequestBody DireccionesDTO d){auth.requerirAdmin(t);return ResponseEntity.ok(service.actualizar(id,d));}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@RequestHeader(value="X-Session-Token",required=false)String t,@PathVariable Long id){auth.requerirAdmin(t);service.eliminar(id);return ResponseEntity.noContent().build();}
}
