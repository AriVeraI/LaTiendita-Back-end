package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.ResponseEnvioDTO;
import com.tienditayeya.tyback_end.model.EnvioModel;
import com.tienditayeya.tyback_end.repository.EnvioRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class EnvioService {
    private final EnvioRepository envioRepository;

    public EnvioService(EnvioRepository envioRepository) {
        this.envioRepository = envioRepository;
    }

    public ResponseEnvioDTO crearEnvio(EnvioModel envio) {
        if (envioRepository.existsByNumeroDeRastreo(envio.getNumeroDeRastreo())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "El numero de rastreo ya existe"
            );
        }

        // Mapeo DTO -> Entidad
        EnvioModel model = new EnvioModel();
        model.setPaqueteria(envio.getPaqueteria());
        model.setNumeroDeRastreo(envio.getNumeroDeRastreo());
        model.setEstadoDeEnvio(envio.getEstadoDeEnvio());
        model.setFechaDespacho(envio.getFechaDespacho());
        model.setFechaEntregaEstimada(envio.getFechaEntregaEstimada());

        EnvioModel envioGuardado = envioRepository.save(model);

        //Mapeo Entidad -> Dto
        return convertirDTO(envioGuardado);
    }

    @Transactional
    public ResponseEnvioDTO obtenerNumeroRastreo(Long numeroDeRastreo){
        return envioRepository.findByIdNumeroDeRastreo(numeroDeRastreo).map(this::convertirDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No se encontro ningún envío con el número de ratreo " + numeroDeRastreo));
    }

    //Metodo helper de mapeo
    private ResponseEnvioDTO convertirDTO(EnvioModel model) {
        return new ResponseEnvioDTO(
                model.getId(),
                model.getPaqueteria(),
                model.getNumeroDeRastreo(),
                model.getEstadoDeEnvio(),
                model.getFechaDespacho(),
                model.getFechaEntregaEstimada()
        );
    }
}
