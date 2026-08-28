package com.tienditayeya.tyback_end.controller;

import com.tienditayeya.tyback_end.dto.CrearPedidoRequest;
import com.tienditayeya.tyback_end.dto.PedidoResponse;
import com.tienditayeya.tyback_end.service.AuthSessionService;
import com.tienditayeya.tyback_end.service.CheckoutService;
import com.tienditayeya.tyback_end.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping({"/api/pedidos", "/api/orders"})
public class PedidoController {

    private final PedidoService pedidoService;
    private final CheckoutService checkoutService;
    private final AuthSessionService authSessionService;

    public PedidoController(PedidoService pedidoService,
                            CheckoutService checkoutService,
                            AuthSessionService authSessionService) {
        this.pedidoService = pedidoService;
        this.checkoutService = checkoutService;
        this.authSessionService = authSessionService;
    }

    @GetMapping
    public List<PedidoResponse> obtenerTodos(
            @RequestHeader(value = "X-Session-Token", required = false) String token) {
        authSessionService.requerirAdmin(token);
        return pedidoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> obtenerPorId(
            @RequestHeader(value = "X-Session-Token", required = false) String token,
            @PathVariable Long id) {
        authSessionService.requerirAdmin(token);
        return ResponseEntity.ok(pedidoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> crearPedido(
            @RequestHeader(value = "X-Session-Token", required = false) String token,
            @Valid @RequestBody CrearPedidoRequest request) {
        AuthSessionService.SesionUsuario sesion = authSessionService.requerirSesion(token);
        if (!sesion.idUsuario().equals(request.idUsuario())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No puedes crear pedidos para otro usuario");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(checkoutService.procesarCompra(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(
            @RequestHeader(value = "X-Session-Token", required = false) String token,
            @PathVariable Long id) {
        authSessionService.requerirAdmin(token);
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
