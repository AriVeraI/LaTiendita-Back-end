package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.PagoDTO;
import java.util.List;

public interface PagoService {
    List<PagoDTO> listarTodos();
    PagoDTO obtenerPorId(Integer id);
    PagoDTO guardar(PagoDTO dto);
    PagoDTO actualizar(Integer id, PagoDTO dto);
    void eliminar(Integer id);
}