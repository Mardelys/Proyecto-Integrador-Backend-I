package com.example.ClinicaOdontologica.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ClinicaOdontologica.entity.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByEmail(String email);
}
