package com.example.appGestioneAziendale.services;

import com.example.appGestioneAziendale.domain.dto.requests.LikeRequest;
import com.example.appGestioneAziendale.domain.dto.response.LikeResponse;
import com.example.appGestioneAziendale.domain.entities.Dipendente;
import com.example.appGestioneAziendale.domain.entities.Like;
import com.example.appGestioneAziendale.domain.entities.News;
import com.example.appGestioneAziendale.domain.exceptions.MyEntityNotFoundException;
import com.example.appGestioneAziendale.mappers.LikeMapper;
import com.example.appGestioneAziendale.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private NewsService newsService;
    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private LikeMapper likeMapper;

    public LikeResponse createLike(LikeRequest request) {
        // Recupero News e Dipendente
        News news = newsService.getById(request.NewsId());
        Dipendente dipendente = dipendenteService.getById(request.DipendenteId());

        // Verifico se ci sta il duplicato del like
        if (likeRepository.existsByNewsAndDipendente(news, dipendente)) {
            throw new IllegalArgumentException(
                    "Il dipendente con ID " + dipendente.getId() + " ha già messo like alla news con ID " + news.getId()
            );
        }

        // Creazione del like
        Like like = likeMapper.toEntity(news, dipendente);
        likeRepository.save(like);

        // Ritorna la risposta del Like creato
        return likeMapper.toResponse(like);
    }

    public Like getById(Long id) {
        return likeRepository.findById(id)
                .orElseThrow(() -> new MyEntityNotFoundException("Like non trovato con id: " + id));
    }

    public List<Like> getAll() {
        return likeRepository.findAll();
    }

    public void deleteById(Long id) {
        if (!likeRepository.existsById(id)) {
            throw new MyEntityNotFoundException("Like con id " + id + " non trovato!"
            );
        }
        likeRepository.deleteById(id);
    }


}
