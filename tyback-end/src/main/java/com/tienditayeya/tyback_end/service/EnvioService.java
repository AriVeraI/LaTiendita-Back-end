package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.RequestEnvioDTO;
import com.tienditayeya.tyback_end.dto.ResponseEnvioDTO;
import com.tienditayeya.tyback_end.model.EnvioModel;
import com.tienditayeya.tyback_end.model.Pedido;
import com.tienditayeya.tyback_end.repository.EnvioRepository;
import com.tienditayeya.tyback_end.repository.PedidoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EnvioService {

    private final EnvioRepository envioRepository;
    private final PedidoRepository pedidoRepository;

    public EnvioService(EnvioRepository envioRepository, PedidoRepository pedidoRepository) {
        this.envioRepository = envioRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    public ResponseEnvioDTO crearEnvio(RequestEnvioDTO request) {
        if (envioRepository.existsByNumeroDeRastreo(request.numeroDeRastreo())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El número de rastreo ya existe.");
        }

        Pedido pedido = pedidoRepository.findById(request.idPedidos())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No se encontró el pedido con ID: " + request.idPedidos()));

        EnvioModel model = new EnvioModel();
        model.setPaqueteria(request.paqueteria());
        model.setNumeroDeRastreo(request.numeroDeRastreo());
        model.setEstadoDeEnvio(request.estadoDeEnvio());
        model.setFechaDespacho(request.fechaDespacho());
        model.setFechaEntregaEstimada(request.fechaEntregaEstimada());
        model.setPedido(pedido);

        EnvioModel envioGuardado = envioRepository.save(model);
        return convertirDTO(envioGuardado);
    }

    @Transactional(readOnly = true)
    public List<ResponseEnvioDTO> obtenerTodosLosEnvios() {
        return envioRepository.findAll()
                .stream()
                .map(this::convertirDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public ResponseEnvioDTO obtenerNumeroRastreo(String numeroDeRastreo) {
        return envioRepository.findByNumeroDeRastreo(numeroDeRastreo)
                .map(this::convertirDTO)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No se encontró ningún envío con el número de rastreo: " + numeroDeRastreo));
    }

    private ResponseEnvioDTO convertirDTO(EnvioModel model) {
        return new ResponseEnvioDTO(
                model.getId(),
                model.getPaqueteria(),
                model.getNumeroDeRastreo(),
                model.getEstadoDeEnvio(),
                model.getFechaDespacho(),
                model.getFechaEntregaEstimada(),
                model.getPedido() != null ? model.getPedido().getIdPedidos() : null
        );
    }
}