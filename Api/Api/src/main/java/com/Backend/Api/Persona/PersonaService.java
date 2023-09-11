package com.Backend.Api.Persona;
import java.util.Optional;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PersonaService {
    private final PersonaRepository personaRepo;

    public PersonaService(PersonaRepository personaRepo) {
        this.personaRepo = personaRepo;
    }

    public void createPersona(persona persona) {
        if (persona.getNombre() != null && persona.getGenero() != null && persona.getDescripcion() != null) {
            personaRepo.save(persona);
        } else {
            throw new IllegalArgumentException("Los campos nombre, género y descripción no pueden ser nulos");
        }
    }

     public List<persona> obtenerTodasLasPersonas() {
         return personaRepo.findAll();
     }

    public void eliminar(int id) {
        personaRepo.deleteById(id);
    }
   
    
    public Optional<persona> obtenerPersonaPorId(int id) {
        return personaRepo.findById(id);
    }

    public void actualizarPersona(int id, persona personaActualizada) {
        Optional<persona> personaExistente = personaRepo.findById(id);

        if (personaExistente.isPresent()) {
            persona persona = personaExistente.get();
            persona.setNombre(personaActualizada.getNombre());
            persona.setGenero(personaActualizada.getGenero());
            persona.setDescripcion(personaActualizada.getDescripcion());

            personaRepo.save(persona);
        } else {
            throw new IllegalArgumentException("No se encontró la persona con ID: " + id);
        }
    }
    
}
