package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.CategoriaDTO;
import com.tienditayeya.tyback_end.model.Categoria;
import com.tienditayeya.tyback_end.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    private CategoriaDTO convertirADto(Categoria categoria) {
        return new CategoriaDTO(
                categoria.getIdCategoria(),
                categoria.getNombreCategoria(),
                categoria.getSlug()
        );
    }

    private Categoria convertirAEntidad(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(dto.getIdCategoria());
        categoria.setNombreCategoria(dto.getNombreCategoria());
        categoria.setSlug(dto.getSlug());
        return categoria;
    }

    public List<CategoriaDTO> listarCategorias() {
        return categoriaRepository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    public Optional<CategoriaDTO> buscarPorId(Integer id) {
        return categoriaRepository.findById(id)
                .map(this::convertirADto);
    }

    public CategoriaDTO guardarCategoria(CategoriaDTO categoriaDto) {
        Categoria categoria = convertirAEntidad(categoriaDto);
        Categoria guardada = categoriaRepository.save(categoria);
        return convertirADto(guardada);
    }

    public void eliminarCategoria(Integer id) {
        categoriaRepository.deleteById(id);
    }
}