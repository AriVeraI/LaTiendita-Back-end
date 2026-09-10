package com.tienditayeya.tyback_end.repository;

import com.tienditayeya.tyback_end.model.EnvioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnvioRepository extends JpaRepository<EnvioModel, Long> {
    Optional<EnvioModel> findByNumeroRastreo(String numeroRastreo);
    boolean existsByNumeroRastreo(String numeroRastreo);
    Optional<EnvioModel> findByPedidosIdPedidos(Long pedidoId);
}
