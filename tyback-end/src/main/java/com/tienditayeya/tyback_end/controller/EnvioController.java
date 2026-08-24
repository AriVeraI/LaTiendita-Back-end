package com.tienditayeya.tyback_end.controller;

import com.tienditayeya.tyback_end.dto.ResponseEnvioDTO;
import com.tienditayeya.tyback_end.dto.RequetsEnvioDTO;
import com.tienditayeya.tyback_end.model.EnvioModel;
import com.tienditayeya.tyback_end.service.EnvioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/envios")
public class EnvioController {
    private final EnvioService envioService;
    private EnvioModel requets;

    public EnvioController(EnvioService envioService){
        this.envioService = envioService;
    }

    @PostMapping
    public ResponseEntity<ResponseEnvioDTO> registrarEnvio(@Validated @RequestBody RequetsEnvioDTO request){
        ResponseEnvioDTO nuevoEnvio = envioService.crearEnvio(requets);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEnvio);
    }

    @GetMapping("rastreo/{numeroDeRastreo}")
    public ResponseEntity<ResponseEnvioDTO> consultarPorRastreo(@PathVariable("numeroDeRastreo") Long numeroDeRastreo){
        ResponseEnvioDTO envio = envioService.obtenerNumeroRastreo(numeroDeRastreo);
        return ResponseEntity.ok(envio);
    }
}
