package com.example.appGestioneAziendale.domain.dto.requests;

import com.example.appGestioneAziendale.domain.entities.Dipendente;

import java.time.LocalDateTime;
import java.util.List;

public record TimbraturaRequest(
        LocalDateTime ingresso,
        LocalDateTime uscita,
        LocalDateTime inizioPausa,
        LocalDateTime finePausa,
        List<Dipendente> idDipendente
) {
}
