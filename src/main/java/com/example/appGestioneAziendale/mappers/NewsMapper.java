package com.example.appGestioneAziendale.mappers;

import com.example.appGestioneAziendale.domain.dto.requests.CreateNewsRequest;
import com.example.appGestioneAziendale.domain.dto.response.NewsResponse;
import com.example.appGestioneAziendale.domain.entities.News;
import com.example.appGestioneAziendale.domain.exceptions.MyEntityNotFoundException;
import com.example.appGestioneAziendale.services.DipendenteService;

import java.util.stream.Collectors;

public class NewsMapper {

    private DipendenteService dipendenteService;

    public News fromCreateNewsRequest(CreateNewsRequest request) throws MyEntityNotFoundException{
        return News.builder()
                .titolo(request.titolo())
                .testo(request.testo())
                .image_url(request.image_url())
                .allegato_url(request.allegato())
                .idPublisher((com.example.appGestioneAziendale.domain.entities.Dipendente) request.idPublisher()
                        .stream()
                        .map(id -> {
                        return dipendenteService.getById(id);
                        }).collect(Collectors.toSet()))
                .build();
    }

    public NewsResponse toNewsResponse(News news){
        return NewsResponse.builder()
                .id(news.getId())
                .titolo(news.getTitolo())
                .testo(news.getTesto())
                .image_url(news.getImage_url())
                .allegato(news.getAllegato_url())
                .build();
    }
}
