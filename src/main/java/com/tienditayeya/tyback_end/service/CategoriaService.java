package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.CategoriaDTO;
import com.tienditayeya.tyback_end.model.Categoria;
import com.tienditayeya.tyback_end.repository.CategoriaRepository;
import com.tienditayeya.tyback_end.repository.ProductoHasCategoriaRepository;
import com.tienditayeya.tyback_end.repository.SubcategoriaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.text.Normalizer;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ProductoHasCategoriaRepository productoCategoriaRepository;
    private final SubcategoriaRepository subcategoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository,
                            ProductoHasCategoriaRepository productoCategoriaRepository,
                            SubcategoriaRepository subcategoriaRepository) {
        this.categoriaRepository = categoriaRepository;
        this.productoCategoriaRepository = productoCategoriaRepository;
        this.subcategoriaRepository = subcategoriaRepository;
    }

    private CategoriaDTO convertirADto(Categoria categoria) {
        return new CategoriaDTO(categoria.getIdCategoria(), categoria.getNombreCategoria(), categoria.getSlug());
    }

    public List<CategoriaDTO> listarCategorias() {
        return categoriaRepository.findAll().stream().map(this::convertirADto).collect(Collectors.toList());
    }

    public Optional<CategoriaDTO> buscarPorId(Integer id) {
        return categoriaRepository.findById(id).map(this::convertirADto);
    }

    @Transactional
    public CategoriaDTO guardarCategoria(CategoriaDTO dto) {
        String nombre = dto.getNombreCategoria() == null ? "" : dto.getNombreCategoria().trim();
        if (nombre.length() < 2 || nombre.length() > 45) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de categoría debe tener entre 2 y 45 caracteres");
        }
        String slug = normalizarSlug(dto.getSlug() == null || dto.getSlug().isBlank() ? nombre : dto.getSlug());
        if (slug.isBlank() || slug.length() > 45) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Slug de categoría inválido");
        }

        categoriaRepository.findByNombreCategoriaIgnoreCase(nombre).ifPresent(existente -> {
            if (dto.getIdCategoria() == null || !existente.getIdCategoria().equals(dto.getIdCategoria())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe una categoría con ese nombre");
            }
        });
        categoriaRepository.findBySlugIgnoreCase(slug).ifPresent(existente -> {
            if (dto.getIdCategoria() == null || !existente.getIdCategoria().equals(dto.getIdCategoria())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe una categoría con ese slug");
            }
        });

        Categoria categoria;
        if (dto.getIdCategoria() != null) {
            categoria = categoriaRepository.findById(dto.getIdCategoria())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoría no encontrada"));
        } else {
            categoria = new Categoria();
        }
        categoria.setNombreCategoria(nombre);
        categoria.setSlug(slug);
        return convertirADto(categoriaRepository.save(categoria));
    }

    @Transactional
    public void eliminarCategoria(Integer id) {
        if (!categoriaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoría no encontrada");
        }
        long productos = productoCategoriaRepository.countByCategoriasIdCategoria(id);
        long subcategorias = subcategoriaRepository.countByCategoria_IdCategoria(id);
        if (productos > 0 || subcategorias > 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "La categoría está en uso por " + productos + " producto(s) y " + subcategorias + " subcategoría(s)");
        }
        int eliminadas = categoriaRepository.deleteDirectById(id);
        if (eliminadas != 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La categoría cambió antes de poder eliminarse. Recarga e intenta otra vez");
        }
    }

    private String normalizarSlug(String texto) {
        String sinAcentos = Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        return sinAcentos.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9]+", "-").replaceAll("(^-|-$)", "");
    }
}
