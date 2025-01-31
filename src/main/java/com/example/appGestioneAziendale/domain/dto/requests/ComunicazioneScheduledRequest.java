package com.example.appGestioneAziendale.domain.dto.requests;

import com.example.appGestioneAziendale.domain.entities.Dipendente;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ComunicazioneScheduledRequest(

        @NotNull
        Long id,
        @NotBlank
        String testo,
        @Future
        LocalDateTime publishTime,
        @NotBlank
        String allegato_url,
        @NotNull
        Dipendente idDipendente

) {
}
