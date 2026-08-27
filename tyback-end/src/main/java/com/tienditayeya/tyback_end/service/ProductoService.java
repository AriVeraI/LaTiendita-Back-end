package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.ProductoDTO;
import com.tienditayeya.tyback_end.model.Categoria;
import com.tienditayeya.tyback_end.model.ImagenProducto;
import com.tienditayeya.tyback_end.model.Producto;
import com.tienditayeya.tyback_end.model.ProductoHasCategoria;
import com.tienditayeya.tyback_end.repository.CategoriaRepository;
import com.tienditayeya.tyback_end.repository.ImagenProductoRepository;
import com.tienditayeya.tyback_end.repository.ProductoHasCategoriaRepository;
import com.tienditayeya.tyback_end.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoHasCategoriaRepository productoHasCategoriaRepository;
    private final CategoriaRepository categoriaRepository;
    private final ImagenProductoRepository imagenProductoRepository;

    public ProductoService(
            ProductoRepository productoRepository,
            ProductoHasCategoriaRepository productoHasCategoriaRepository,
            CategoriaRepository categoriaRepository,
            ImagenProductoRepository imagenProductoRepository) {

        this.productoRepository = productoRepository;
        this.productoHasCategoriaRepository = productoHasCategoriaRepository;
        this.categoriaRepository = categoriaRepository;
        this.imagenProductoRepository = imagenProductoRepository;
    }

    // ======================================================
    // OBTENER TODOS LOS PRODUCTOS
    // ======================================================

    public List<ProductoDTO> obtenerTodos() {

        List<Producto> productos = productoRepository.findAll();

        List<ProductoDTO> productosDTO = new ArrayList<>();

        for (Producto producto : productos) {
            productosDTO.add(convertirADTO(producto));
        }

        return productosDTO;
    }


    // ======================================================
    // OBTENER PRODUCTO COMO ENTIDAD
    // Se utiliza para relaciones entre tablas
    // ======================================================

    public Optional<Producto> obtenerEntidadPorId(Integer id) {

        return productoRepository.findById(id);
    }


    // ======================================================
    // OBTENER PRODUCTO COMO DTO
    // Se utiliza para enviar información al Frontend
    // ======================================================

    public Optional<ProductoDTO> obtenerPorId(Integer id) {

        Optional<Producto> producto = productoRepository.findById(id);

        if (producto.isPresent()) {

            return Optional.of(convertirADTO(producto.get()));

        }

        return Optional.empty();
    }


    // ======================================================
    // CREAR PRODUCTO
    // ======================================================

    public Producto guardar(Producto producto) {

        return productoRepository.save(producto);
    }


    // ======================================================
    // ACTUALIZAR PRODUCTO
    // ======================================================

    public Producto actualizar(Producto producto) {

        return productoRepository.save(producto);
    }


    // ======================================================
    // ELIMINAR PRODUCTO
    // ======================================================

    public void eliminar(Integer id) {

        productoRepository.deleteById(id);
    }


    // ======================================================
    // CONVERTIR PRODUCTO A DTO
    // ======================================================

    private ProductoDTO convertirADTO(Producto producto) {

        String categoria = null;
        String imagen = null;


        // --------------------------------------------------
        // BUSCAR CATEGORÍA
        // --------------------------------------------------

        List<ProductoHasCategoria> relaciones =
                productoHasCategoriaRepository
                        .findByProductosIdProductos(
                                producto.getIdProductos()
                        );


        if (!relaciones.isEmpty()) {

            Integer categoriaId =
                    relaciones.get(0).getCategoriasIdCategoria();

            Optional<Categoria> categoriaEncontrada =
                    categoriaRepository.findById(categoriaId);

            if (categoriaEncontrada.isPresent()) {

                categoria =
                        categoriaEncontrada
                                .get()
                                .getNombreCategoria();
            }
        }


        // --------------------------------------------------
        // BUSCAR IMAGEN
        // --------------------------------------------------

        List<ImagenProducto> imagenes =
                imagenProductoRepository
                        .findByProductosIdProductos(
                                producto.getIdProductos().longValue()
                        );


        if (!imagenes.isEmpty()) {

            imagen =
                    imagenes.get(0).getUrlImagen();
        }


        // --------------------------------------------------
        // CREAR DTO
        // --------------------------------------------------

        ProductoDTO dto = new ProductoDTO();

        dto.setIdProductos(producto.getIdProductos());
        dto.setSku(producto.getSku());
        dto.setNombreProducto(producto.getNombreProducto());
        dto.setDescripcionProducto(producto.getDescripcionProducto());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setDisponibilidad(producto.getDisponibilidad());

        dto.setCategoria(categoria);
        dto.setImagen(imagen);

        return dto;
    }
}