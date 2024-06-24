package com.example.ClinicaOdontologica.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ClinicaOdontologica.entity.Paciente;
import java.util.Optional;



public interface PacienteRepository extends JpaRepository<Paciente,Long>{
    Optional<Paciente> findByEmail(String email);

}
