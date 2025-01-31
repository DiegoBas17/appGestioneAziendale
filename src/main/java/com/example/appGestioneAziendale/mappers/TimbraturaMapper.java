package com.example.appGestioneAziendale.mappers;

import com.example.appGestioneAziendale.domain.dto.requests.TimbraturaRequest;
import com.example.appGestioneAziendale.domain.entities.Dipendente;
import com.example.appGestioneAziendale.domain.entities.Timbratura;
import com.example.appGestioneAziendale.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimbraturaMapper {
    @Autowired
    private DipendenteRepository dipendenteRepository;

    public Timbratura fromTimbraturaRequest(TimbraturaRequest request) {
        Dipendente dipendente = dipendenteRepository
                .findById(request.idDipendente())
                .orElseThrow(() -> new IllegalArgumentException("Dipartimento con ID : " + request.idDipendente() + " non trovato"));
        if (request.ingresso() == null && request.inizioPausa() == null && request.finePausa() == null && request.uscita() == null)
            System.out.println("Ma che richiesta mi stai facendo?");

        return Timbratura.builder()
                .ingresso(LocalDateTime.now())
                .uscita(request.uscita())
                .inizioPausaPranzo(request.inizioPausa())
                .finePausaPranzo(request.finePausa())
                .idDipendente(dipendente)
                .build();
    }
}
