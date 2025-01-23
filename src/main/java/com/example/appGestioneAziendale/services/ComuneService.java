package com.example.appGestioneAziendale.services;

import com.example.appGestioneAziendale.domain.dto.requests.ComuneRequest;
import com.example.appGestioneAziendale.domain.entities.Comune;
import com.example.appGestioneAziendale.domain.exceptions.MyEntityNotFoundException;
import com.example.appGestioneAziendale.repository.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComuneService {
    @Autowired
    private ComuneRepository comuneRepository;

    public Comune saveComune(ComuneRequest newComune) {
        return Comune.builder().nome(newComune.nome()).regione(newComune.regione()).build();
    }

    public Comune getById(Long id) throws MyEntityNotFoundException {
        return comuneRepository.findById(id)
                .orElseThrow(() -> new MyEntityNotFoundException("Comune non trovato"));
    }
}
