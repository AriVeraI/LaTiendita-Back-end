package com.tienditayeya.tyback_end.service;
import com.tienditayeya.tyback_end.dto.DireccionesDTO;
import com.tienditayeya.tyback_end.model.Direcciones;
import com.tienditayeya.tyback_end.repository.DireccionesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DireccionesService {

    private final DireccionesRepository direccionRepository;

    public DireccionesService(DireccionesRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
    }

    @Transactional(readOnly = true)
    public List<DireccionesDTO> listarTodas() {
        return direccionRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public DireccionesDTO obtenerPorId(Integer id) {
        return direccionRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada con ID: " + id));
    }

    @Transactional
    public DireccionesDTO crear(DireccionesDTO dto) {
        Direcciones direccion = mapToEntity(dto);
        Direcciones guardada = direccionRepository.save(direccion);
        return mapToDTO(guardada);
    }

    @Transactional
    public DireccionesDTO actualizar(Integer id, DireccionesDTO dto) {
        Direcciones direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada con ID: " + id));

        direccion.setCalle(dto.calle());
        direccion.setNumero(dto.numero());
        direccion.setColonia(dto.colonia());
        direccion.setCiudad(dto.ciudad());
        direccion.setEstado(dto.estado());
        direccion.setCodigoPostal(dto.codigoPostal());
        direccion.setUsuarioId(dto.usuarioId());

        return mapToDTO(direccionRepository.save(direccion));
    }

    @Transactional
    public void eliminar(Integer id) {
        if (!direccionRepository.existsById(id)) {
            throw new RuntimeException("Dirección no encontrada con ID: " + id);
        }
        direccionRepository.deleteById(id);
    }

    private DireccionesDTO mapToDTO(Direcciones entity) {
        return new DireccionesDTO(
                entity.getIdDireccion(),
                entity.getCalle(),
                entity.getNumero(),
                entity.getColonia(),
                entity.getCudad(),
                entity.getEstado(),
                entity.getCodigoPostal(),
                entity.getUsuarioId()
        );
    }

    private Direcciones mapToEntity(DireccionesDTO dto) {
        return Direcciones.builder()
                .idDireccion(dto.idDireccion())
                .calle(dto.calle())
                .numero(dto.numero())
                .colonia(dto.colonia())
                .ciudad(dto.ciudad())
                .estado(dto.estado())
                .codigoPostal(dto.codigoPostal())
                .usuarioId(dto.usuarioId())
                .build();
    }
}