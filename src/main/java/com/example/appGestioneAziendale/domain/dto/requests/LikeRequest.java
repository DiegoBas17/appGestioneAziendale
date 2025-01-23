package com.example.appGestioneAziendale.domain.dto.requests;

import jakarta.validation.constraints.NotBlank;

public record LikeRequest(
    @NotBlank(message = "NewsId non può essere blank o null")
    Long NewsId,
    @NotBlank(message = "DipendenteId non può essere blank o null")
    Long DipendenteId
) {
}
