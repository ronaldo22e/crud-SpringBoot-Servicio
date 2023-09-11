package com.Backend.Api.Persona;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<persona, Integer> {
    // No necesitas añadir un método deleteById aquí, ya está definido en JpaRepository
    // Puedes añadir otros métodos personalizados aquí si es necesario
}

