package com.tienditayeya.tyback_end.service;


import com.tienditayeya.tyback_end.dto.ImagenProductoDTO;
import com.tienditayeya.tyback_end.model.ImagenProducto;
import com.tienditayeya.tyback_end.model.Producto;
import com.tienditayeya.tyback_end.repository.ImagenProductoRepository;
import com.tienditayeya.tyback_end.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImagenProductoService {

    @Autowired
    private ImagenProductoRepository imagenProductoRepository;

    @Autowired
    private ProductoRepository productoRepository; // la crea quien haga "productos"

    public List<ImagenProductoDTO> listarTodas() {
        return imagenProductoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public ImagenProductoDTO obtenerPorId(Long id) {
        ImagenProducto imagen = imagenProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada con id: " + id));
        return convertirADTO(imagen);
    }

    public ImagenProductoDTO guardar(ImagenProductoDTO dto) {
        // 1. Buscamos el Producto real usando el id que llega en el DTO.
        Producto producto = productoRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + dto.getProductoId()));

        // 2. Creamos la ImagenProducto y la CONECTAMOS con el producto encontrado.
        //    Esta línea arma la relación (FK) hacia productos.
        ImagenProducto imagen = new ImagenProducto();
        imagen.setUrlImagen(dto.getUrlImagen());
        imagen.setProducto(producto);

        // 3. Guardamos y devolvemos el DTO de salida.
        ImagenProducto imagenGuardada = imagenProductoRepository.save(imagen);
        return convertirADTO(imagenGuardada);
    }

    public ImagenProductoDTO actualizar(Long id, ImagenProductoDTO dto) {
        ImagenProducto imagen = imagenProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada con id: " + id));

        Producto producto = productoRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + dto.getProductoId()));

        imagen.setUrlImagen(dto.getUrlImagen());
        imagen.setProducto(producto);

        ImagenProducto imagenActualizada = imagenProductoRepository.save(imagen);
        return convertirADTO(imagenActualizada);
    }

    public void eliminar(Long id) {
        imagenProductoRepository.deleteById(id);
    }

    // Convierte la Entity (con el objeto Producto completo) al DTO (con solo el id del producto)
    private ImagenProductoDTO convertirADTO(ImagenProducto imagen) {
        return new ImagenProductoDTO(
                imagen.getIdImagenesProductos(),
                imagen.getUrlImagen(),
                imagen.getProducto().getIdProductos() // <-- aquí se "desempaca" la relación a solo un id
        );
    }
}