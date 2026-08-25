package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.VariantesRequestDTO;
import com.tienditayeya.tyback_end.model.Variantes;
import com.tienditayeya.tyback_end.repository.VariantesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VariantesService {private final VariantesRepository variantesRepository;

    public VariantesService(VariantesRepository variantesRepository) {
        this.variantesRepository = variantesRepository;
    }

    @Transactional(readOnly = true)
    public List<Variantes> findAll() {
        return variantesRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Variantes findById(Long id) {
        return variantesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Variante no encontrada"));
    }

    @Transactional
    public Variantes create(VariantesRequestDTO dto) {
        Variantes variantes = new Variantes();
        variantes.setSkuVariantes(dto.getSkuVariantes());
        variantes.setAtributos(dto.getAtributos());
        variantes.setStockVariantes(dto.getStockVariantes());
        return variantesRepository.save(variantes);
    }

    @Transactional
    public Variantes update(Long id, VariantesRequestDTO dto) {
        Variantes variantes = findById(id);
        variantes.setSkuVariantes(dto.getSkuVariantes());
        variantes.setAtributos(dto.getAtributos());
        variantes.setStockVariantes(dto.getStockVariantes());
        return variantesRepository.save(variantes);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!variantesRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Variante no encontrada");
        }
        variantesRepository.deleteById(id);
    }
}
