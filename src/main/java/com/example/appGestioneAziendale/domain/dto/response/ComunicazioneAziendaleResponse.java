package com.example.appGestioneAziendale.domain.dto.response;

import lombok.Builder;

@Builder
public record ComunicazioneAziendaleResponse(
        Long id,
        String testo,
        String allegato_url

) {

}
