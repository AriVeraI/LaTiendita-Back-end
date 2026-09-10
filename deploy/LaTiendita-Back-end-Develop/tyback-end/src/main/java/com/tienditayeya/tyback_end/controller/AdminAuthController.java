package com.tienditayeya.tyback_end.controller;

import com.tienditayeya.tyback_end.dto.LoginRequest;
import com.tienditayeya.tyback_end.dto.UsuarioResponse;
import com.tienditayeya.tyback_end.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminAuthController {

    private final UsuarioService usuarioService;

    public AdminAuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponse> loginAdmin(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(usuarioService.loginAdmin(request));
    }
}
