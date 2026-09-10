package com.tienditayeya.tyback_end.controller;

import com.tienditayeya.tyback_end.dto.ImagenProductoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import com.tienditayeya.tyback_end.model.ImagenProducto;
import com.tienditayeya.tyback_end.service.ImagenProductoService;
import com.tienditayeya.tyback_end.service.AuthSessionService;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/imagenes")
public class ImagenProductoController {

    private final ImagenProductoService imagenService;
    private final AuthSessionService authSessionService;

    public ImagenProductoController(ImagenProductoService imagenService, AuthSessionService authSessionService) {
        this.imagenService = imagenService;
        this.authSessionService = authSessionService;
    }

    @GetMapping
    public List<ImagenProducto> listarTodas() {
        return imagenService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Optional<ImagenProducto> buscarPorId(@PathVariable Long id) { // Cambiado a Long
        return imagenService.obtenerPorId(id);
    }

    @PostMapping
    public ImagenProducto crearImagen(@RequestHeader(value="X-Session-Token", required=false) String token, @RequestBody ImagenProducto imagen) {
        authSessionService.requerirAdmin(token);
        return imagenService.guardarImagen(imagen);
    }

    @DeleteMapping("/{id}")
    public String eliminarImagen(@RequestHeader(value="X-Session-Token", required=false) String token, @PathVariable Long id) { // Cambiado a Long
        authSessionService.requerirAdmin(token);
        imagenService.eliminarImagen(id);
        return "Imagen eliminada con éxito";
    }
}