package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.ImagenProductoDTO;
import java.util.List;

public interface ImagenProductoService {
    List<ImagenProductoDTO> listarTodas();
    ImagenProductoDTO obtenerPorId(Integer id);
    ImagenProductoDTO guardar(ImagenProductoDTO dto);
    ImagenProductoDTO actualizar(Integer id, ImagenProductoDTO dto);
    void eliminar(Integer id);
}