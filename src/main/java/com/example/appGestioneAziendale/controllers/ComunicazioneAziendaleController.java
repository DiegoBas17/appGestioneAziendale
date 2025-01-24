package com.example.appGestioneAziendale.controllers;

import com.example.appGestioneAziendale.domain.dto.requests.ComunicazioneAziendaleRequest;
import com.example.appGestioneAziendale.domain.dto.response.ComunicazioneAziendaleResponse;
import com.example.appGestioneAziendale.services.ComunicazioneAziendaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/app/v1/comunicazioneAziendale")
@RequiredArgsConstructor
public class ComunicazioneAziendaleController {

    @Autowired
    private ComunicazioneAziendaleService comunicazioneAziendaleService;

    @PostMapping("/create")
    public ResponseEntity<ComunicazioneAziendaleResponse> createComunicazioneAziendale(
            @RequestBody ComunicazioneAziendaleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(comunicazioneAziendaleService.createComunicazioneAziendale(request));
    }



}
