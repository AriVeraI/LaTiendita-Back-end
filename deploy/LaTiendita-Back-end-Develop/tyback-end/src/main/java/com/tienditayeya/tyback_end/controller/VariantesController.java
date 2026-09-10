package com.tienditayeya.tyback_end.controller;
import com.tienditayeya.tyback_end.dto.VariantesRequestDTO;
import com.tienditayeya.tyback_end.model.Variantes;
import com.tienditayeya.tyback_end.service.AuthSessionService;
import com.tienditayeya.tyback_end.service.VariantesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/variantes")
public class VariantesController{
 private final VariantesService service; private final AuthSessionService auth;
 public VariantesController(VariantesService service,AuthSessionService auth){this.service=service;this.auth=auth;}
 @GetMapping public List<Variantes> all(){return service.findAll();}
 @GetMapping("/{id}") public Variantes one(@PathVariable Long id){return service.findById(id);}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public Variantes create(@RequestHeader(value="X-Session-Token",required=false)String t,@RequestBody VariantesRequestDTO d){auth.requerirAdmin(t);return service.create(d);}
 @PutMapping("/{id}") public Variantes update(@RequestHeader(value="X-Session-Token",required=false)String t,@PathVariable Long id,@RequestBody VariantesRequestDTO d){auth.requerirAdmin(t);return service.update(id,d);}
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@RequestHeader(value="X-Session-Token",required=false)String t,@PathVariable Long id){auth.requerirAdmin(t);service.deleteById(id);}
}
