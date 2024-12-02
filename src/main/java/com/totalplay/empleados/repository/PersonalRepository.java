package com.totalplay.empleados.repository;

import com.totalplay.empleados.model.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {
    boolean existsByNombreAndApellidoPaternoAndApellidoMaterno(String nombre, String apellidoPaterno, String apellidoMaterno);
    @Procedure(name = "subir_foto")
    void subir_foto(@Param("p_id") Long id, @Param("p_foto_url") String fotoUrl);
}

