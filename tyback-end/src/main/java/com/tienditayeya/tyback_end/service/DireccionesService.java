package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.DireccionesDTO;
import com.tienditayeya.tyback_end.model.Direcciones;
import com.tienditayeya.tyback_end.model.Usuario;
import com.tienditayeya.tyback_end.repository.DireccionesRepository;
import com.tienditayeya.tyback_end.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DireccionesService {

    private final DireccionesRepository direccionRepository;
    private final UsuarioRepository usuarioRepository; // 1. Inyectamos el repositorio de usuarios

    public DireccionesService(DireccionesRepository direccionRepository, UsuarioRepository usuarioRepository) {
        this.direccionRepository = direccionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<DireccionesDTO> listarTodas() {
        return direccionRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public DireccionesDTO obtenerPorId(Long id) {
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
    public DireccionesDTO actualizar(Long id, DireccionesDTO dto) {
        Direcciones direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada con ID: " + id));

        direccion.setCalle(dto.getCalle());
        direccion.setNumero(dto.getNumero());
        direccion.setColonia(dto.getColonia());
        direccion.setCiudad(dto.getCiudad());
        direccion.setEstado(dto.getEstado());
        direccion.setCodigoPostal(dto.getCodigoPostal());

        // Buscamos el usuario en la BD antes de asignarlo
        Usuario usuario = usuarioRepository.findById(dto.getUsuariosId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getUsuariosId()));
        direccion.setUsuario(usuario);

        return mapToDTO(direccionRepository.save(direccion));
    }

    @Transactional
    public void eliminar(Long id) {
        if (!direccionRepository.existsById(id)) {
            throw new RuntimeException("Dirección no encontrada con ID: " + id);
        }
        direccionRepository.deleteById(id);
    }

    private DireccionesDTO mapToDTO(Direcciones entity) {
        DireccionesDTO dto = new DireccionesDTO();
        dto.setIdDireccion(entity.getIdDireccion());
        dto.setCalle(entity.getCalle());
        dto.setNumero(entity.getNumero());
        dto.setColonia(entity.getColonia());
        dto.setCiudad(entity.getCiudad());
        dto.setEstado(entity.getEstado());
        dto.setCodigoPostal(entity.getCodigoPostal());

        // Extraemos el ID del objeto Usuario si existe
        if (entity.getUsuario() != null) {
            dto.setUsuariosId(entity.getUsuario().getIdUsuario());
        }

        return dto;
    }

    private Direcciones mapToEntity(DireccionesDTO dto) {
        // Buscamos el usuario para asignarlo en el builder
        Usuario usuario = usuarioRepository.findById(dto.getUsuariosId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getUsuariosId()));

        return Direcciones.builder()
                .idDireccion(dto.getIdDireccion())
                .calle(dto.getCalle())
                .numero(dto.getNumero())
                .colonia(dto.getColonia())
                .ciudad(dto.getCiudad())
                .estado(dto.getEstado())
                .codigoPostal(dto.getCodigoPostal())
                .usuario(usuario) // Asignamos el objeto Usuario completo
                .build();
    }
}