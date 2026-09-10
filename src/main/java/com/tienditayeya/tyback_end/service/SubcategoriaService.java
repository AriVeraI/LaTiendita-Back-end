package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.SubcategoriaDTO;
import com.tienditayeya.tyback_end.model.Categoria;
import com.tienditayeya.tyback_end.model.Subcategoria;
import com.tienditayeya.tyback_end.repository.CategoriaRepository;
import com.tienditayeya.tyback_end.repository.SubcategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubcategoriaService {

    @Autowired
    private SubcategoriaRepository subcategoriaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Convertir de Entidad a DTO
    private SubcategoriaDTO convertirADto(Subcategoria subcategoria) {
        Integer idCat = (subcategoria.getCategoria() != null) ? subcategoria.getCategoria().getIdCategoria() : null;
        return new SubcategoriaDTO(
                subcategoria.getIdSubcategoria(),
                subcategoria.getNombreSubcategoria(),
                idCat
        );
    }

    // Convertir de DTO a Entidad (buscando la categoría asociada en la BD)
    private Subcategoria convertirAEntidad(SubcategoriaDTO dto) {
        Subcategoria subcategoria = new Subcategoria();
        subcategoria.setIdSubcategoria(dto.getIdSubcategoria());
        subcategoria.setNombreSubcategoria(dto.getNombreSubcategoria());

        // Buscamos la categoría por su ID para asignarla a la entidad
        if (dto.getIdCategoria() != null) {
            Categoria categoria = categoriaRepository.findById(dto.getIdCategoria()).orElse(null);
            subcategoria.setCategoria(categoria);
        }

        return subcategoria;
    }

    public List<SubcategoriaDTO> listarSubcategorias() {
        return subcategoriaRepository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    public Optional<SubcategoriaDTO> buscarPorId(Integer id) {
        return subcategoriaRepository.findById(id)
                .map(this::convertirADto);
    }

    public SubcategoriaDTO guardarSubcategoria(SubcategoriaDTO subcategoriaDto) {
        Subcategoria subcategoria = convertirAEntidad(subcategoriaDto);
        Subcategoria guardada = subcategoriaRepository.save(subcategoria);
        return convertirADto(guardada);
    }

    public void eliminarSubcategoria(Integer id) {
        subcategoriaRepository.deleteById(id);
    }
}