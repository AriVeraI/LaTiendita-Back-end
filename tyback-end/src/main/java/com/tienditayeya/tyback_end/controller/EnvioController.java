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
@RequestMapping("/api/envios")
public class EnvioController {
    private final EnvioService envioService;

    public EnvioController(EnvioService envioService){
        this.envioService = envioService;
    }

    @PostMapping
    public ResponseEntity<ResponseEnvioDTO> registrarEnvio(@Validated @RequestBody RequetsEnvioDTO requets) {
        EnvioModel nuevoEnvioModel = new EnvioModel();

        // Asigna el valor del campo que Hibernate marca como nulo
        nuevoEnvioModel.setEstadoDeEnvio(requets.estadoDeEnvio());

        // Asigna el resto de campos correspondientes
        nuevoEnvioModel.setNumeroDeRastreo(requets.numeroDeRastreo());
        nuevoEnvioModel.setPaqueteria(requets.paqueteria());
        nuevoEnvioModel.setFechaDespacho(requets.fechaDespacho());
        nuevoEnvioModel.setFechaEntregaEstimada(requets.fechaEntregaEstimada());

        ResponseEnvioDTO nuevoEnvio = envioService.crearEnvio(nuevoEnvioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEnvio);
    }

    @GetMapping("/rastreo/{numeroDeRastreo}")
    public ResponseEntity<ResponseEnvioDTO> consultarPorRastreo(@PathVariable("numeroDeRastreo") Long numeroDeRastreo){
        ResponseEnvioDTO envio = envioService.obtenerNumeroRastreo(numeroDeRastreo);
        return ResponseEntity.ok(envio);
    }
}
