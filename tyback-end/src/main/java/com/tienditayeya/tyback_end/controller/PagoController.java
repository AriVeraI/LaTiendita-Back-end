package com.tienditayeya.tyback_end.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.tienditayeya.tyback_end.model.Pago;
import com.tienditayeya.tyback_end.service.PagoService;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public List<Pago> listarTodos() {
        return pagoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Pago> buscarPorId(@PathVariable Long id) { // Cambiado a Long
        return pagoService.obtenerPorId(id);
    }

    @PostMapping
    public Pago crearPago(@RequestBody Pago pago) {
        return pagoService.guardarPago(pago);
    }

    @DeleteMapping("/{id}")
    public String eliminarPago(@PathVariable Long id) { // Cambiado a Long
        pagoService.eliminarPago(id);
        return "Pago eliminado con éxito";
    }
}