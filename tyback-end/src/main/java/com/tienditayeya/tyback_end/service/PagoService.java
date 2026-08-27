package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.PagoDTO;
import com.tienditayeya.tyback_end.model.Pago;
import com.tienditayeya.tyback_end.model.Pedido;
import com.tienditayeya.tyback_end.repository.PagoRepository;
import com.tienditayeya.tyback_end.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<PagoDTO> listarTodos() {
        return pagoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public PagoDTO obtenerPorId(Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con id: " + id));
        return convertirADTO(pago);
    }

    public PagoDTO guardar(PagoDTO dto) {
        Pedido pedido = pedidoRepository.findById(dto.getPedidoId())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + dto.getPedidoId()));

        Pago pago = new Pago();
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setMonto(dto.getMonto());
        pago.setFechaPago(dto.getFechaPago());
        pago.setPedido(pedido);

        Pago pagoGuardado = pagoRepository.save(pago);
        return convertirADTO(pagoGuardado);
    }

    public PagoDTO actualizar(Long id, PagoDTO dto) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con id: " + id));

        Pedido pedido = pedidoRepository.findById(dto.getPedidoId())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + dto.getPedidoId()));

        pago.setMetodoPago(dto.getMetodoPago());
        pago.setMonto(dto.getMonto());
        pago.setFechaPago(dto.getFechaPago());
        pago.setPedido(pedido);

        Pago pagoActualizado = pagoRepository.save(pago);
        return convertirADTO(pagoActualizado);
    }

    public void eliminar(Long id) {
        pagoRepository.deleteById(id);
    }

    private PagoDTO convertirADTO(Pago pago) {
        return new PagoDTO(
                pago.getIdPagos(),
                pago.getMetodoPago(),
                pago.getMonto(),
                pago.getFechaPago(),
                pago.getPedido().getIdPedidos()
        );
    }
}