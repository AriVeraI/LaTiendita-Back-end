package com.tienditayeya.tyback_end.service;

import java.util.List;
import com.tienditayeya.tyback_end.model.ImagenProducto;
import com.tienditayeya.tyback_end.repository.ImagenProductoRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ImagenProductoService {

    private final ImagenProductoRepository imagenRepository;

    public ImagenProductoService(ImagenProductoRepository imagenRepository) {
        this.imagenRepository = imagenRepository;
    }

    public ImagenProducto guardarImagen(ImagenProducto imagen) {
        return imagenRepository.save(imagen);
    }

    public List<ImagenProducto> obtenerTodas() {
        return imagenRepository.findAll();
    }

    // Cambiado el ID a Long
    public Optional<ImagenProducto> obtenerPorId(Long id) {
        return imagenRepository.findById(id);
    }

    // Cambiado el ID a Long
    public void eliminarImagen(Long id) {
        imagenRepository.deleteById(id);
    }
}