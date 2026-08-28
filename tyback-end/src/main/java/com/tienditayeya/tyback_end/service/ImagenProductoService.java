package com.tienditayeya.tyback_end.service;

import java.util.List;
import java.util.Optional;
import com.tienditayeya.tyback_end.model.ImagenProducto;
import com.tienditayeya.tyback_end.repository.ImagenProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImagenProductoService {
    private final ImagenProductoRepository imagenRepository;
    public ImagenProductoService(ImagenProductoRepository imagenRepository){this.imagenRepository=imagenRepository;}
    public ImagenProducto guardarImagen(ImagenProducto imagen){return imagenRepository.save(imagen);}
    public List<ImagenProducto> obtenerTodas(){return imagenRepository.findAll();}
    public Optional<ImagenProducto> obtenerPorId(Long id){return imagenRepository.findById(id);}
    @Transactional public void eliminarImagen(Long id){imagenRepository.deleteDirectById(id);}
}
