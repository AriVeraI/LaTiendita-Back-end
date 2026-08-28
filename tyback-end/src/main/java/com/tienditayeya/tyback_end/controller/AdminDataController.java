package com.tienditayeya.tyback_end.controller;

import com.tienditayeya.tyback_end.dto.AdminCreateRequest;
import com.tienditayeya.tyback_end.dto.AdminSummaryResponse;
import com.tienditayeya.tyback_end.dto.AdminProductoRequest;
import com.tienditayeya.tyback_end.dto.CatalogProductoResponse;
import com.tienditayeya.tyback_end.dto.ClienteAdminResponse;
import com.tienditayeya.tyback_end.dto.PedidoAdminResponse;
import com.tienditayeya.tyback_end.dto.UsuarioResponse;

import com.tienditayeya.tyback_end.service.AdminDashboardService;
import com.tienditayeya.tyback_end.service.AuthSessionService;
import com.tienditayeya.tyback_end.service.UsuarioService;
import com.tienditayeya.tyback_end.service.PedidoService;
import com.tienditayeya.tyback_end.service.AdminProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminDataController {
    private final AuthSessionService authSessionService;
    private final AdminDashboardService dashboardService;
    private final UsuarioService usuarioService;
    private final PedidoService pedidoService;
    private final AdminProductoService adminProductoService;

    public AdminDataController(AuthSessionService authSessionService,
                               AdminDashboardService dashboardService,
                               UsuarioService usuarioService,
                               PedidoService pedidoService,
                               AdminProductoService adminProductoService) {
        this.authSessionService = authSessionService;
        this.dashboardService = dashboardService;
        this.usuarioService = usuarioService;
        this.pedidoService = pedidoService;
        this.adminProductoService = adminProductoService;
    }

    @GetMapping("/summary")
    public AdminSummaryResponse resumen(@RequestHeader(value="X-Session-Token", required=false) String token) {
        authSessionService.requerirAdmin(token);
        return dashboardService.resumen();
    }

    @GetMapping("/clients")
    public List<ClienteAdminResponse> clientes(@RequestHeader(value="X-Session-Token", required=false) String token) {
        authSessionService.requerirAdmin(token);
        return dashboardService.clientes();
    }


    @GetMapping("/orders")
    public List<PedidoAdminResponse> pedidos(@RequestHeader(value="X-Session-Token", required=false) String token) {
        authSessionService.requerirAdmin(token);
        return dashboardService.pedidos();
    }

    @GetMapping("/users")
    public List<UsuarioResponse> usuarios(@RequestHeader(value="X-Session-Token", required=false) String token) {
        authSessionService.requerirAdmin(token);
        return usuarioService.listarUsuarios();
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse crearAdmin(@RequestHeader(value="X-Session-Token", required=false) String token,
                                      @Valid @RequestBody AdminCreateRequest request) {
        authSessionService.requerirAdmin(token);
        return usuarioService.registrarAdmin(request);
    }


    @PatchMapping("/users/{id}/role")
    public UsuarioResponse cambiarRol(@RequestHeader(value="X-Session-Token", required=false) String token,
                                      @PathVariable Long id,
                                      @RequestParam String role) {
        AuthSessionService.SesionUsuario sesion = authSessionService.requerirAdmin(token);
        if (!List.of("admin", "user").contains(role.toLowerCase())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rol no válido");
        }
        if (sesion.idUsuario().equals(id) && "user".equalsIgnoreCase(role)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "No puedes revocar tu propio acceso de administrador");
        }
        return usuarioService.cambiarRol(id, role.toLowerCase());
    }

    @PatchMapping("/orders/{id}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void actualizarEstado(@RequestHeader(value="X-Session-Token", required=false) String token,
                                 @PathVariable Long id,
                                 @RequestParam String estado) {
        authSessionService.requerirAdmin(token);
        if (!List.of("Pendiente", "Procesando", "Pagado", "Enviado", "Entregado", "Cancelado").contains(estado)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estado de pedido no válido");
        }
        pedidoService.actualizarEstado(id, estado);
    }
    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public CatalogProductoResponse crearProducto(@RequestHeader(value="X-Session-Token", required=false) String token,
                                                  @Valid @RequestBody AdminProductoRequest request) {
        authSessionService.requerirAdmin(token);
        return adminProductoService.crear(request);
    }

    @PutMapping("/products/{id}")
    public CatalogProductoResponse actualizarProducto(@RequestHeader(value="X-Session-Token", required=false) String token,
                                                       @PathVariable Integer id,
                                                       @Valid @RequestBody AdminProductoRequest request) {
        authSessionService.requerirAdmin(token);
        return adminProductoService.actualizar(id, request);
    }

}
