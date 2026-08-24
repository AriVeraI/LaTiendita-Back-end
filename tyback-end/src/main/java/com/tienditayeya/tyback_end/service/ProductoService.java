package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.model.Producto;
import com.tienditayeya.tyback_end.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {


    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    //como hacer un select * from productos
    public List<Producto> obtenerTodos(){
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerPorId(Integer id){
        return productoRepository.findById(id);
    }

    //se utiliza para crear y actualizar, revisa el id para saber qué ejecutar
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizar(Producto producto) {
        return productoRepository.save(producto);
    }
    public void eliminar(Integer id) {
        productoRepository.deleteById(id);
    }
}
