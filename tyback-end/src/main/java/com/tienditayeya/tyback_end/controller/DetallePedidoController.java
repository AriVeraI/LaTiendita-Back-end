package com.tienditayeya.tyback_end.controller;

import com.tienditayeya.tyback_end.dto.DetallePedidoDTO;
import com.tienditayeya.tyback_end.service.AuthSessionService;
import com.tienditayeya.tyback_end.service.DetallePedidoService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/detalles-pedido")
public class DetallePedidoController {
    private final DetallePedidoService service; private final AuthSessionService auth;
    public DetallePedidoController(DetallePedidoService service, AuthSessionService auth){this.service=service;this.auth=auth;}
    @GetMapping public ResponseEntity<List<DetallePedidoDTO>> getAll(@RequestHeader(value="X-Session-Token",required=false) String t){auth.requerirAdmin(t);return ResponseEntity.ok(service.findAll());}
    @GetMapping("/{id}") public ResponseEntity<DetallePedidoDTO> getById(@RequestHeader(value="X-Session-Token",required=false) String t,@PathVariable Long id){auth.requerirAdmin(t);return ResponseEntity.ok(service.findById(id));}
    @GetMapping("/pedido/{pedidoId}") public ResponseEntity<List<DetallePedidoDTO>> getByPedidoId(@RequestHeader(value="X-Session-Token",required=false) String t,@PathVariable Long pedidoId){auth.requerirAdmin(t);return ResponseEntity.ok(service.findByPedidoId(pedidoId));}
    @PostMapping public ResponseEntity<DetallePedidoDTO> create(@RequestHeader(value="X-Session-Token",required=false) String t,@RequestBody DetallePedidoDTO dto){auth.requerirAdmin(t);return new ResponseEntity<>(service.save(dto),HttpStatus.CREATED);}
    @PutMapping("/{id}") public ResponseEntity<DetallePedidoDTO> update(@RequestHeader(value="X-Session-Token",required=false) String t,@PathVariable Long id,@RequestBody DetallePedidoDTO dto){auth.requerirAdmin(t);return ResponseEntity.ok(service.update(id,dto));}
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@RequestHeader(value="X-Session-Token",required=false) String t,@PathVariable Long id){auth.requerirAdmin(t);service.deleteById(id);return ResponseEntity.noContent().build();}
}
