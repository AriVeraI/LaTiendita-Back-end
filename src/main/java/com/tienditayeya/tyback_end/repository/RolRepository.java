package com.tienditayeya.tyback_end.repository;

import com.tienditayeya.tyback_end.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    boolean existsByRolUsuario(String rolUsuario);

    Optional<Rol> findByRolUsuarioIgnoreCase(String rolUsuario);
}