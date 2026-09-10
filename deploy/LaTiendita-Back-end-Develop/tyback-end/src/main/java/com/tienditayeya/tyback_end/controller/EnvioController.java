package com.tienditayeya.tyback_end.controller;
import com.tienditayeya.tyback_end.dto.*;
import com.tienditayeya.tyback_end.service.*;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping({"/envios","/api/envios"})
public class EnvioController{
 private final EnvioService service; private final AuthSessionService auth;
 public EnvioController(EnvioService service,AuthSessionService auth){this.service=service;this.auth=auth;}
 @PostMapping public ResponseEntity<ResponseEnvioDTO> create(@RequestHeader(value="X-Session-Token",required=false)String t,@Valid @RequestBody RequetsEnvioDTO d){auth.requerirAdmin(t);return ResponseEntity.status(HttpStatus.CREATED).body(service.crearEnvio(d));}
 @GetMapping("/rastreo/{n}") public ResponseEntity<ResponseEnvioDTO> track(@PathVariable("n")String n){return ResponseEntity.ok(service.obtenerNumeroRastreo(n));}
}
