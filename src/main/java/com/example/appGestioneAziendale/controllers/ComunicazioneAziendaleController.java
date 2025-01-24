package com.example.appGestioneAziendale.controllers;

import com.example.appGestioneAziendale.domain.dto.requests.ComunicazioneAziendaleRequest;
import com.example.appGestioneAziendale.domain.dto.response.EntityIdResponse;
import com.example.appGestioneAziendale.services.ComunicazioneAziendaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/comunicazioniAziendali")
public class ComunicazioneAziendaleController {
    @Autowired
    private ComunicazioneAziendaleService comunicazioneAziendaleService;

    @PostMapping("/create/{idDipendente}")
    public ResponseEntity<EntityIdResponse> createComunicazioneAziendale(
            @PathVariable Long idDipendente,
            @RequestBody ComunicazioneAziendaleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(comunicazioneAziendaleService.createComunicazioneAziendale(idDipendente, request));
    }
}
