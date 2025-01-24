package com.example.appGestioneAziendale.mappers;

import com.example.appGestioneAziendale.domain.dto.requests.LikeRequest;
import com.example.appGestioneAziendale.domain.dto.response.LikeResponse;
import com.example.appGestioneAziendale.domain.entities.Dipendente;
import com.example.appGestioneAziendale.domain.entities.Like;
import com.example.appGestioneAziendale.domain.entities.News;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeMapper {

    public Like toEntity(LikeRequest request, News news, Dipendente dipendente) {
        return Like.builder()
                .news(news)
                .dipendente(dipendente)
                .build();
    }

    public LikeResponse toResponse(Like entity) {
        return new LikeResponse(
                entity.getId(),
                entity.getNews().getId(),
                entity.getDipendente().getId()
        );
    }





}
