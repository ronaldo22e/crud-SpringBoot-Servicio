package com.Backend.Api.Persona;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Api/personas")
@CrossOrigin(origins = "http://localhost/Front-SpringBoot/index.html") // origen donde se recibira los datos del back
public class personaController {
    private final PersonaService personaService;

    @Autowired
    public personaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping
    public ResponseEntity<List<persona>> obtenerTodasLasPersonas() {
        List<persona> personas = personaService.obtenerTodasLasPersonas();
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> crearPersona(@RequestBody persona nuevaPersona) {
        try {
            personaService.createPersona(nuevaPersona);
            return new ResponseEntity<>("Persona creada exitosamente", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Error al crear persona: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        try {
            personaService.eliminar(id);
            return new ResponseEntity<>("Persona eliminada exitosamente", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Error al eliminar persona: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

   

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizar(@PathVariable int id, @RequestBody persona personaActualizada) {
        try {
            personaService.actualizarPersona(id, personaActualizada);
            return new ResponseEntity<>("Persona actualizada exitosamente", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Error al actualizar persona: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
 @GetMapping("/{id}")
public ResponseEntity<persona> obtenerPersonaPorId(@PathVariable int id) {
    Optional<persona> persona = personaService.obtenerPersonaPorId(id);
    if (persona.isPresent()) {
        return new ResponseEntity<>(persona.get(), HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
}
