package com.tienditayeya.tyback_end.repository;

import com.tienditayeya.tyback_end.model.EnvioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnvioRepository extends JpaRepository<EnvioModel, Long> {
    // Se utiliza el findById para el buscar el numero de rastreo del envio y se crea un booleano para ver si existe o no el envio
    Optional<EnvioModel> findByNumeroDeRastreo(String numeroDeRastreo);
    boolean existsByNumeroDeRastreo(String numeroDeRastreo);
}

