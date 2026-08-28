package com.tienditayeya.tyback_end.config;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String,Object>> status(ResponseStatusException ex){
        return body(HttpStatus.valueOf(ex.getStatusCode().value()), ex.getReason()==null?"Solicitud no válida":ex.getReason());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> validacion(MethodArgumentNotValidException ex){
        String mensaje=ex.getBindingResult().getFieldErrors().stream().findFirst().map(e->e.getField()+": "+e.getDefaultMessage()).orElse("Datos inválidos");
        return body(HttpStatus.BAD_REQUEST,mensaje);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String,Object>> integridad(DataIntegrityViolationException ex){
        return body(HttpStatus.CONFLICT,"La operación viola una relación o valor único de la base de datos");
    }
    private ResponseEntity<Map<String,Object>> body(HttpStatus status,String message){
        Map<String,Object> m=new LinkedHashMap<>();m.put("timestamp",Instant.now().toString());m.put("status",status.value());m.put("error",status.getReasonPhrase());m.put("message",message);return ResponseEntity.status(status).body(m);
    }
}
