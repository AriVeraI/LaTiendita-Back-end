/*package com.tienditayeya.tyback_end.controller;
import com.tienditayeya.tyback_end.dto.VariantesHPRequestDTO;
import com.tienditayeya.tyback_end.model.VariantesHP;
import com.tienditayeya.tyback_end.service.VariantesHPService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variantes-productos")
public class VariantesHPController {

    private final VariantesHPService service;

    public VariantesHPController(VariantesHPService service) {
        this.service = service;
    }

    @GetMapping
    public List<VariantesHP> getAll() {
        return service.findAll();
    }

    @GetMapping("/producto/{productoId}")
    public List<VariantesHP> getByProducto(@PathVariable Long productoId) {
        return service.findByProductoId(productoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VariantesHP create(@RequestBody VariantesHPRequestDTO dto) {
        return service.create(dto);
    }

    @DeleteMapping("/{variantesId}/{productoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long variantesId, @PathVariable Long productoId) {
        service.delete(variantesId, productoId);
    }
}*/
