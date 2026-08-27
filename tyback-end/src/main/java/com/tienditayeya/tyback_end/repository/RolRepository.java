package com.tienditayeya.tyback_end.repository;

import com.tienditayeya.tyback_end.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long>{

    boolean existsByRolUsuario(String rolUsuario);

}
