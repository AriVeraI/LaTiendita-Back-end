package com.tienditayeya.tyback_end.controller;
import com.tienditayeya.tyback_end.model.Pago;
import com.tienditayeya.tyback_end.service.AuthSessionService;
import com.tienditayeya.tyback_end.service.PagoService;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api/pagos")
public class PagoController{
 private final PagoService service; private final AuthSessionService auth;
 public PagoController(PagoService service,AuthSessionService auth){this.service=service;this.auth=auth;}
 @GetMapping public List<Pago> all(@RequestHeader(value="X-Session-Token",required=false)String t){auth.requerirAdmin(t);return service.obtenerTodos();}
 @GetMapping("/{id}") public Optional<Pago> one(@RequestHeader(value="X-Session-Token",required=false)String t,@PathVariable Long id){auth.requerirAdmin(t);return service.obtenerPorId(id);}
 @PostMapping public Pago create(@RequestHeader(value="X-Session-Token",required=false)String t,@RequestBody Pago p){auth.requerirAdmin(t);return service.guardarPago(p);}
 @DeleteMapping("/{id}") public void delete(@RequestHeader(value="X-Session-Token",required=false)String t,@PathVariable Long id){auth.requerirAdmin(t);service.eliminarPago(id);}
}
