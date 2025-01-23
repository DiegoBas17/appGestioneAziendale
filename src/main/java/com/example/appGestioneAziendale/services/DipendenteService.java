package com.example.appGestioneAziendale.services;

import com.example.appGestioneAziendale.domain.entities.Dipendente;
import com.example.appGestioneAziendale.domain.exceptions.MyEntityNotFoundException;
import com.example.appGestioneAziendale.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;

    public Dipendente getById(Long id) {
        return dipendenteRepository.findById(id).orElseThrow(() -> new MyEntityNotFoundException("Dipendente con id: " + id + " non trovato!"));
    }
}
