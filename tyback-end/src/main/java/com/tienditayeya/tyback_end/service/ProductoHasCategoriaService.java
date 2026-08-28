package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.ProductoHasCategoriaDTO;
import com.tienditayeya.tyback_end.model.ProductoHasCategoria;
import com.tienditayeya.tyback_end.model.ProductoHasCategoriaId;
import com.tienditayeya.tyback_end.repository.ProductoHasCategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoHasCategoriaService {
    private final ProductoHasCategoriaRepository repository;
    public ProductoHasCategoriaService(ProductoHasCategoriaRepository repository){this.repository=repository;}
    private ProductoHasCategoriaDTO toDto(ProductoHasCategoria e){return new ProductoHasCategoriaDTO(e.getProductosIdProductos(),e.getCategoriasIdCategoria());}
    public List<ProductoHasCategoriaDTO> listarTodos(){return repository.findAll().stream().map(this::toDto).toList();}
    public Optional<ProductoHasCategoriaDTO> buscarPorId(Integer p,Integer c){return repository.findById(new ProductoHasCategoriaId(p,c)).map(this::toDto);}
    public ProductoHasCategoriaDTO guardar(ProductoHasCategoriaDTO dto){return toDto(repository.save(new ProductoHasCategoria(dto.getProductosIdProductos(),dto.getCategoriasIdCategoria())));}
    @Transactional public void eliminar(Integer p,Integer c){repository.deleteDirect(p,c);}
}
