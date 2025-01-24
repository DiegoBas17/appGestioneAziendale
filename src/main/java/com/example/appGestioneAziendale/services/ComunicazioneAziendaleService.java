package com.example.appGestioneAziendale.services;

import com.example.appGestioneAziendale.domain.dto.requests.ComunicazioneAziendaleRequest;
import com.example.appGestioneAziendale.domain.dto.response.EntityIdResponse;
import com.example.appGestioneAziendale.domain.entities.ComunicazioneAziendale;
import com.example.appGestioneAziendale.mappers.ComunicazioneAziendaleMapper;
import com.example.appGestioneAziendale.repository.ComunicazioneAziendaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComunicazioneAziendaleService {
    @Autowired
    private ComunicazioneAziendaleRepository comunicazioneAziendaleRepository;
    @Autowired
    private ComunicazioneAziendaleMapper comunicazioneAziendaleMapper;

    public EntityIdResponse createComunicazioneAziendale(Long idDipendente, ComunicazioneAziendaleRequest request) {
        ComunicazioneAziendale comunicazioneAziendale = comunicazioneAziendaleMapper.toEntity(idDipendente, request);
        return new EntityIdResponse(comunicazioneAziendaleRepository.save(comunicazioneAziendale).getId());
    }

}
