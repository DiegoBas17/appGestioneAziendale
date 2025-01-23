package com.example.appGestioneAziendale.services;

import com.example.appGestioneAziendale.domain.dto.requests.LikeRequest;
import com.example.appGestioneAziendale.domain.dto.response.EntityIdResponse;
import com.example.appGestioneAziendale.domain.dto.response.LikeResponse;
import com.example.appGestioneAziendale.domain.entities.Dipendente;
import com.example.appGestioneAziendale.domain.entities.Like;
import com.example.appGestioneAziendale.domain.entities.News;
import com.example.appGestioneAziendale.domain.exceptions.MyEntityNotFoundException;
import com.example.appGestioneAziendale.mappers.LikeMapper;
import com.example.appGestioneAziendale.repository.DipendenteRepository;
import com.example.appGestioneAziendale.repository.LikeRepository;
import com.example.appGestioneAziendale.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private LikeMapper likeMapper;

    public LikeResponse createLike(LikeRequest request) {
        // Recupero News e Dipendente
        News news = newsRepository.findById(request.NewsId())
                .orElseThrow(() -> new MyEntityNotFoundException("News non trovata con id: " + request.NewsId()));
        Dipendente dipendente = dipendenteRepository.findById(request.DipendenteId())
                .orElseThrow(() -> new MyEntityNotFoundException("Dipendente non trovato con id: " + request.DipendenteId()));

        // Creazione Like
        Like like = likeMapper.toEntity(request, news, dipendente);

        // Salvataggio e conversione in risposta
        Like savedLike = likeRepository.save(like);
        return likeMapper.toResponse(savedLike);
    }


}
