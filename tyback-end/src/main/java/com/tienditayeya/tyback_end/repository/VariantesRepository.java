package com.tienditayeya.tyback_end.repository;

import com.tienditayeya.tyback_end.model.Variantes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantesRepository extends JpaRepository<Variantes, Long> {
    List<Variantes> findByAtributosContainingIgnoreCase(String atributos);
}
