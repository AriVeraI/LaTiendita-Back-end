package com.tienditayeya.tyback_end.controller;

import com.tienditayeya.tyback_end.dto.ImagenProductoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import com.tienditayeya.tyback_end.model.ImagenProducto;
import com.tienditayeya.tyback_end.service.ImagenProductoService;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/imagenes")
public class ImagenProductoController {

    private final ImagenProductoService imagenService;

    public ImagenProductoController(ImagenProductoService imagenService) {
        this.imagenService = imagenService;
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
    public ImagenProducto crearImagen(@RequestBody ImagenProducto imagen) {
        return imagenService.guardarImagen(imagen);
    }

    @DeleteMapping("/{id}")
    public String eliminarImagen(@PathVariable Long id) { // Cambiado a Long
        imagenService.eliminarImagen(id);
        return "Imagen eliminada con éxito";
    }
}