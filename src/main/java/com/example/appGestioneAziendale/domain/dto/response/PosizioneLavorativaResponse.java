package com.example.appGestioneAziendale.domain.dto.response;

public record PosizioneLavorativaResponse(

        Long id,

        String nome,

        String descrizione,

        Long idDipartimento
) {}
