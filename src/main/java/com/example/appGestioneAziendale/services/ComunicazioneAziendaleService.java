package com.example.appGestioneAziendale.services;

import com.example.appGestioneAziendale.domain.dto.requests.ComunicazioneAziendaleRequest;
import com.example.appGestioneAziendale.domain.dto.response.ComunicazioneAziendaleResponse;
import com.example.appGestioneAziendale.domain.dto.response.EntityIdResponse;
import com.example.appGestioneAziendale.domain.entities.ComunicazioneAziendale;
import com.example.appGestioneAziendale.domain.exceptions.MyEntityNotFoundException;
import com.example.appGestioneAziendale.mappers.ComunicazioneAziendaleMapper;
import com.example.appGestioneAziendale.repository.ComunicazioneAziendaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComunicazioneAziendaleService {
    @Autowired
    private ComunicazioneAziendaleRepository comunicazioneAziendaleRepository;
    @Autowired
    private ComunicazioneAziendaleMapper comunicazioneAziendaleMapper;

    public ComunicazioneAziendale getById(Long id) throws MyEntityNotFoundException {
        return comunicazioneAziendaleRepository
                .findById(id)
                .orElseThrow(
                        () -> new MyEntityNotFoundException("Le comunicazioni aziendali con id " + id + " non esistono"));
    }

    public List<ComunicazioneAziendale> getAll() {
        return comunicazioneAziendaleRepository.findAll();
    }

    public EntityIdResponse createComunicazioneAziendale(Long idDipendente, ComunicazioneAziendaleRequest request) {
        ComunicazioneAziendale comunicazioneAziendale = comunicazioneAziendaleMapper.toEntity(idDipendente, request);
        return new EntityIdResponse(comunicazioneAziendaleRepository.save(comunicazioneAziendale).getId());
    }

    public EntityIdResponse updateComunicazioneAziendale(Long id, ComunicazioneAziendaleResponse response) {
        ComunicazioneAziendale comunicazioneAziendale = comunicazioneAziendaleRepository
                .findById(id)
                .orElseThrow(
                        () -> new MyEntityNotFoundException("La comunicazione aziendale con id " + id + " non esiste"));
        comunicazioneAziendale.setTesto(response.testo());
        comunicazioneAziendale.setAllegato_url(response.allegato_url());

        return new EntityIdResponse(comunicazioneAziendaleRepository.save(comunicazioneAziendale).getId());
    }

    public void deleteById(Long id) {
        comunicazioneAziendaleRepository.deleteById(id);
    }
}
