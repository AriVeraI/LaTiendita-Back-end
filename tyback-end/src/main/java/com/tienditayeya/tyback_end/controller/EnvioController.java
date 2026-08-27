package com.tienditayeya.tyback_end.controller;

import com.tienditayeya.tyback_end.dto.ResponseEnvioDTO;
import com.tienditayeya.tyback_end.dto.RequestEnvioDTO;
import com.tienditayeya.tyback_end.service.EnvioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {
    private final EnvioService envioService;

    public EnvioController(EnvioService envioService){
        this.envioService = envioService;
    }

    @PostMapping
    public ResponseEntity<ResponseEnvioDTO> registrarEnvio(@Validated @RequestBody RequestEnvioDTO requets){
        ResponseEnvioDTO nuevoEnvio = envioService.crearEnvio(requets);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEnvio);
    }

    @GetMapping
    public ResponseEntity<List<ResponseEnvioDTO>> listarTodosLosEnvios() {
        return ResponseEntity.ok(envioService.obtenerTodosLosEnvios());
    }

    @GetMapping("/rastreo/{numeroDeRastreo}")
    public ResponseEntity<ResponseEnvioDTO> obtenerEnvio(@PathVariable("numeroDeRastreo") String numeroDeRastreo){
        return ResponseEntity.ok(envioService.obtenerNumeroRastreo(numeroDeRastreo));
    }
}