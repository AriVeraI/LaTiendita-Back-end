package com.tienditayeya.tyback_end.repository;

import com.tienditayeya.tyback_end.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    // como puede devolver un valor nullo o existente se usa OPTIONAL
    Optional<Usuario> findByEmail(String email);



}
