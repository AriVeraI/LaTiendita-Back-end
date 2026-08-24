package com.tienditayeya.tyback_end.controller;

import com.tienditayeya.tyback_end.dto.VariantesRequestDTO;
import com.tienditayeya.tyback_end.model.Variantes;
import com.tienditayeya.tyback_end.service.VariantesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variantes")

public class VariantesController {

    private final VariantesService variantesService;

    public VariantesController(VariantesService variantesService) {
        this.variantesService = variantesService;
    }

    @GetMapping
    public List<Variantes> getAll() {
        return variantesService.findAll();
    }

    @GetMapping("/{id}")
    public Variantes getById(@PathVariable Long id) {
        return variantesService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Variantes create(@RequestBody VariantesRequestDTO dto) {
        return variantesService.create(dto);
    }

    @PutMapping("/{id}")
    public Variantes update(@PathVariable Long id, @RequestBody VariantesRequestDTO dto) {
        return variantesService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        variantesService.deleteById(id);
    }
}
