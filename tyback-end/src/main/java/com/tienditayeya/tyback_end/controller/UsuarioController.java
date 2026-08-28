package com.tienditayeya.tyback_end.controller;

import com.tienditayeya.tyback_end.dto.LoginRequest;
import com.tienditayeya.tyback_end.dto.RegistroUsuarioRequest;
import com.tienditayeya.tyback_end.dto.UsuarioResponse;
import com.tienditayeya.tyback_end.service.AuthSessionService;
import com.tienditayeya.tyback_end.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/users", "/api/usuarios"})
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthSessionService authSessionService;

    public UsuarioController(UsuarioService usuarioService, AuthSessionService authSessionService) {
        this.usuarioService = usuarioService;
        this.authSessionService = authSessionService;
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponse> registrar(@Valid @RequestBody RegistroUsuarioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.registrar(request));
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(usuarioService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader(value = "X-Session-Token", required = false) String token) {
        authSessionService.cerrarSesion(token);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public UsuarioResponse sesionActual(@RequestHeader(value = "X-Session-Token", required = false) String token) {
        return usuarioService.obtenerUsuarioSesion(authSessionService.requerirSesion(token));
    }

    @GetMapping
    public List<UsuarioResponse> listarUsuarios(
            @RequestHeader(value = "X-Session-Token", required = false) String token) {
        authSessionService.requerirAdmin(token);
        return usuarioService.listarUsuarios();
    }
}
