package com.tienditayeya.tyback_end.controller;
import com.tienditayeya.tyback_end.dto.VariantesHPRequestDTO;
import com.tienditayeya.tyback_end.model.VariantesHP;
import com.tienditayeya.tyback_end.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/variantes-productos")
public class VariantesHPController{
 private final VariantesHPService service; private final AuthSessionService auth;
 public VariantesHPController(VariantesHPService service,AuthSessionService auth){this.service=service;this.auth=auth;}
 @GetMapping public List<VariantesHP> all(){return service.findAll();}
 @GetMapping("/producto/{id}") public List<VariantesHP> byProduct(@PathVariable("id")Integer id){return service.findByProductoId(id);}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public VariantesHP create(@RequestHeader(value="X-Session-Token",required=false)String t,@RequestBody VariantesHPRequestDTO d){auth.requerirAdmin(t);return service.create(d);}
 @DeleteMapping("/{varianteId}/{productoId}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@RequestHeader(value="X-Session-Token",required=false)String t,@PathVariable Long varianteId,@PathVariable Integer productoId){auth.requerirAdmin(t);service.delete(varianteId,productoId);}
}
