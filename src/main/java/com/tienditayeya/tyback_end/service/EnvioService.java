package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.RequetsEnvioDTO;
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

    public ResponseEnvioDTO crearEnvio(RequetsEnvioDTO request) {
        if (envioRepository.existsByNumeroRastreo(request.numeroRastreo())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El número de rastreo ya existe");
        }

        EnvioModel model = new EnvioModel();
        model.setPaqueteria(request.paqueteria());
        model.setNumeroRastreo(request.numeroRastreo());
        model.setEstadoEnvio("Preparando");
        model.setFechaDespacho(request.fechaDespacho());
        model.setFechaEntregaEstimada(request.fechaEntregaEstimada());
        model.setPedidosIdPedidos(request.pedidosIdPedidos());

        return convertirDTO(envioRepository.save(model));
    }

    public ResponseEnvioDTO obtenerNumeroRastreo(String numeroRastreo) {
        return envioRepository.findByNumeroRastreo(numeroRastreo)
                .map(this::convertirDTO)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No se encontró ningún envío con el número de rastreo " + numeroRastreo
                ));
    }

    private ResponseEnvioDTO convertirDTO(EnvioModel model) {
        return new ResponseEnvioDTO(
                model.getId(),
                model.getPaqueteria(),
                model.getNumeroRastreo(),
                model.getEstadoEnvio(),
                model.getFechaDespacho(),
                model.getFechaEntregaEstimada(),
                model.getPedidosIdPedidos()
        );
    }
}
