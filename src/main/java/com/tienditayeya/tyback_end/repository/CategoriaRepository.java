package com.tienditayeya.tyback_end.repository;

import com.tienditayeya.tyback_end.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    Optional<Categoria> findBySlugIgnoreCase(String slug);
    Optional<Categoria> findByNombreCategoriaIgnoreCase(String nombreCategoria);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from Categoria c where c.idCategoria = :id")
    int deleteDirectById(@Param("id") Integer id);
}
