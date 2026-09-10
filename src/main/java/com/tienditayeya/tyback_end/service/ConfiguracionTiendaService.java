package com.tienditayeya.tyback_end.service;
import com.tienditayeya.tyback_end.model.ConfiguracionTienda;
import com.tienditayeya.tyback_end.repository.ConfiguracionTiendaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
@Service
public class ConfiguracionTiendaService{
 private final ConfiguracionTiendaRepository repository;
 public ConfiguracionTiendaService(ConfiguracionTiendaRepository repository){this.repository=repository;}
 @Transactional(readOnly=true) public ConfiguracionTienda obtener(){return repository.findById(1).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Configuración no inicializada"));}
 @Transactional public ConfiguracionTienda guardar(ConfiguracionTienda c){c.setId(1);return repository.save(c);}
}
