package com.example.appGestioneAziendale.services;

import com.example.appGestioneAziendale.domain.dto.requests.ComunicazioneAziendaleRequest;
import com.example.appGestioneAziendale.domain.dto.response.ComunicazioneAziendaleResponse;
import com.example.appGestioneAziendale.domain.entities.ComunicazioneAziendale;
import com.example.appGestioneAziendale.mappers.ComunicazioneAziendaleMapper;
import com.example.appGestioneAziendale.repository.ComunicazioneAziendaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComunicazioneAziendaleService {

    @Autowired
    private ComunicazioneAziendaleRepository comunicazioneAziendaleRepository;

    @Autowired
    private ComunicazioneAziendaleMapper comunicazioneAziendaleMapper;

    public ComunicazioneAziendaleResponse createComunicazioneAziendale(ComunicazioneAziendaleRequest request) {
        // Conversione DTO in entità
        ComunicazioneAziendale comunicazioneAziendale = comunicazioneAziendaleMapper.toEntity(request);

        // Salvataggio
        ComunicazioneAziendale salvato = comunicazioneAziendaleRepository.save(comunicazioneAziendale);

        // Conversione in risposta
        return comunicazioneAziendaleMapper.toResponse(salvato);
    }

}
