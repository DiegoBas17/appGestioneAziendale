package com.example.appGestioneAziendale.mappers;

import com.example.appGestioneAziendale.domain.dto.requests.ComunicazioneAziendaleRequest;
import com.example.appGestioneAziendale.domain.dto.response.ComunicazioneAziendaleResponse;
import com.example.appGestioneAziendale.domain.entities.ComunicazioneAziendale;
import com.example.appGestioneAziendale.domain.entities.Dipendente;
import com.example.appGestioneAziendale.repository.DipendenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComunicazioneAziendaleMapper {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public ComunicazioneAziendale toEntity(ComunicazioneAziendaleRequest request) {
        Dipendente dipendente = dipendenteRepository.findById(request.idDipendente())
                .orElseThrow(() -> new IllegalArgumentException("Dipendente non trovato!"));

        return ComunicazioneAziendale.builder()
                .testo(request.testo())
                .allegato_url(request.allegato_url())
                .idDipendente(dipendente)
                .build();
    }

    public ComunicazioneAziendaleResponse toResponse(ComunicazioneAziendale comunicazioneAziendale) {
        return ComunicazioneAziendaleResponse.builder()
                .id(comunicazioneAziendale.getId())
                .testo(comunicazioneAziendale.getTesto())
                .allegato_url(comunicazioneAziendale.getAllegato_url())
                .idDipendente(
                        DipendenteResponse.builder()
                                .id(comunicazioneAziendale.getIdDipendente().getId())
                                .build()
                )
                .build();
    }


}
