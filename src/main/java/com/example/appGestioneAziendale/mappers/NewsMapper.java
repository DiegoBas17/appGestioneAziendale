package com.example.appGestioneAziendale.mappers;

import com.example.appGestioneAziendale.domain.dto.requests.CreateNewsRequest;
import com.example.appGestioneAziendale.domain.dto.response.NewsResponse;
import com.example.appGestioneAziendale.domain.entities.Dipendente;
import com.example.appGestioneAziendale.domain.entities.News;
import com.example.appGestioneAziendale.domain.exceptions.MyEntityNotFoundException;
import com.example.appGestioneAziendale.services.DipendenteService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
@Service
public class NewsMapper {

    private DipendenteService dipendenteService;

    public News fromCreateNewsRequest(CreateNewsRequest request) throws MyEntityNotFoundException {
        return News.builder()
                .titolo(request.titolo())
                .testo(request.testo())
                .image_url(request.image_url())
                .allegato_url(request.allegato())
                .idPublisher((Dipendente) request.idPublisher()
                        .stream()
                        .map(id -> {
                            try {
                                return dipendenteService.getById(id);
                            } catch (MyEntityNotFoundException e) {
                                throw new RuntimeException("Dipendente non trovato per ID: " + id, e);
                            }
                        })
                        .collect(Collectors.toSet()))
                .build();
    }
}
