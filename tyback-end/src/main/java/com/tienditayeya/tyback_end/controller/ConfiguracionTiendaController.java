package com.tienditayeya.tyback_end.controller;

import com.tienditayeya.tyback_end.model.ConfiguracionTienda;
import com.tienditayeya.tyback_end.service.AuthSessionService;
import com.tienditayeya.tyback_end.service.ConfiguracionTiendaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/store-config")
public class ConfiguracionTiendaController {
    private final ConfiguracionTiendaService service;
    private final AuthSessionService auth;
    public ConfiguracionTiendaController(ConfiguracionTiendaService service, AuthSessionService auth){this.service=service;this.auth=auth;}
    @GetMapping public ConfiguracionTienda get(){return service.obtener();}
    @PutMapping public ConfiguracionTienda put(@RequestHeader(value="X-Session-Token",required=false) String token,@RequestBody ConfiguracionTienda c){auth.requerirAdmin(token);return service.guardar(c);}
}
