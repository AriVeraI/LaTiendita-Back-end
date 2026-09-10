package com.tienditayeya.tyback_end.repository;

import com.tienditayeya.tyback_end.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
}
