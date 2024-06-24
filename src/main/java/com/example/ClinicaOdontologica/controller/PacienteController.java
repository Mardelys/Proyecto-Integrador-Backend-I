package com.example.ClinicaOdontologica.controller;

import com.example.ClinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import com.example.ClinicaOdontologica.exception.BadRequestException;
import com.example.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.example.ClinicaOdontologica.entity.Paciente;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;


    @PostMapping //registrar un paciente
    public ResponseEntity<Paciente> registrarUnPaciente(@RequestBody Paciente paciente) throws BadRequestException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorEmail(paciente.getEmail());
        if(pacienteBuscado.isPresent()){
            throw new BadRequestException("El correo electronico ya existe, no se puede crear el paciente");
        }
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Paciente> buscarPacienteID(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPaciente(id);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }else{
            throw new ResourceNotFoundException("No existe  : " + id);
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) throws ResourceNotFoundException{
        // validar si existe o  no
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPaciente(paciente.getId());
        if(pacienteBuscado.isPresent()){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Paciente actualizado con exito");
        } else {
            throw new ResourceNotFoundException("No existe el paciente");
        }

    }
    @GetMapping("/{email}")
    public ResponseEntity<Paciente> buscarPorEmail(@PathVariable String email) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorEmail(email);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }else{
            throw new ResourceNotFoundException("No se encontró el paciente");
        }
    }
    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPaciente(id);
        if(pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("paciente eliminado con exito");
        }else{
            throw new ResourceNotFoundException("No existe el id : " + id);
        }
    }


}