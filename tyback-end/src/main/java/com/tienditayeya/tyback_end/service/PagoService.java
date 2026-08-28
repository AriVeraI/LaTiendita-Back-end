package com.tienditayeya.tyback_end.service;

import java.util.List;
import com.tienditayeya.tyback_end.model.Pago;
import com.tienditayeya.tyback_end.repository.PagoRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public Pago guardarPago(Pago pago) {
        return pagoRepository.save(pago);
    }

    public List<Pago> obtenerTodos() {
        return pagoRepository.findAll();
    }

    public Optional<Pago> obtenerPorId(Long id) {
        return pagoRepository.findById(id);
    }

    public void eliminarPago(Long id) {
        pagoRepository.deleteById(id);
    }
}