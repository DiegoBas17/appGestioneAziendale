package com.example.appGestioneAziendale.domain.dto.requests;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record DipendenteRequest(
        @NotBlank(message = "Il nome non può essere blank o null")
        String nome,
        String cognome,
        String email,
        String password,
        EntityIdRequest comune_di_nascita,
        LocalDate data_di_nascita,
        String telefono,
        String avatar,
        String ruolo
) {
}
