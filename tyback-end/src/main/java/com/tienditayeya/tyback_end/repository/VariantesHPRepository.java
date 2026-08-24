package com.tienditayeya.tyback_end.repository;

import com.tienditayeya.tyback_end.model.VariantesHP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VariantesHPRepository extends JpaRepository<VariantesHP, VariantesHP.VariantesHPId> {
    List<VariantesHP> findById_ProductosIdProductos(Integer productoId);
    List<VariantesHP> findById_VariantesIdVariantes(Long varianteId);
}
