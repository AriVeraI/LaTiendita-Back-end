package com.tienditayeya.tyback_end.repository;

import com.tienditayeya.tyback_end.model.Direcciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DireccionesRepository extends JpaRepository<Direcciones, Long> {
    List<Direcciones> findByUsuario_IdUsuario(Long usuarioId);
}
