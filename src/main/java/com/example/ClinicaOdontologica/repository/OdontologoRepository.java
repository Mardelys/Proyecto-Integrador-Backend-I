package com.example.ClinicaOdontologica.repository;
import java.util.Optional;
import com.example.ClinicaOdontologica.entity.Odontologo;

import org.springframework.data.jpa.repository.JpaRepository;


public interface OdontologoRepository extends JpaRepository<Odontologo,Long> {
    Optional<Odontologo> findByMatricula(String matricula);
}
