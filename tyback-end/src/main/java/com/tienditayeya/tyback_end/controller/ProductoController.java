package com.tienditayeya.tyback_end.controller;

import com.tienditayeya.tyback_end.model.Producto;
import com.tienditayeya.tyback_end.dto.CatalogProductoResponse;
import com.tienditayeya.tyback_end.service.AuthSessionService;
import com.tienditayeya.tyback_end.service.ProductoService;
import com.tienditayeya.tyback_end.service.CatalogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/productos", "/api/products"})
public class ProductoController {

    private final ProductoService productoService;
    private final AuthSessionService authSessionService;
    private final CatalogService catalogService;

    public ProductoController(ProductoService productoService, AuthSessionService authSessionService, CatalogService catalogService){
        this.productoService = productoService;
        this.authSessionService = authSessionService;
        this.catalogService = catalogService;
    }

    @GetMapping
    public List<Producto> obtenerTodos(){
        return productoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Producto> obtenerPorId(@PathVariable Integer id) {
        return productoService.obtenerPorId(id);
    }


    @GetMapping("/catalog")
    public List<CatalogProductoResponse> catalogo(){
        return catalogService.listar();
    }

    @GetMapping("/{id}/catalog")
    public CatalogProductoResponse catalogoPorId(@PathVariable Integer id){
        return catalogService.obtener(id);
    }

    @PostMapping
    public Producto guardar(
            @RequestHeader(value = "X-Session-Token", required = false) String token,
            @RequestBody Producto producto){
        authSessionService.requerirAdmin(token);
        return productoService.guardar(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizar(
            @RequestHeader(value = "X-Session-Token", required = false) String token,
            @PathVariable Integer id,
            @RequestBody Producto producto){
        authSessionService.requerirAdmin(token);
        producto.setIdProductos(id);
        return productoService.actualizar(producto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(
            @RequestHeader(value = "X-Session-Token", required = false) String token,
            @PathVariable Integer id) {
        authSessionService.requerirAdmin(token);
        productoService.eliminar(id);
    }
}
