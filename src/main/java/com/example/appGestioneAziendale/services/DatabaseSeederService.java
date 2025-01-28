package com.example.appGestioneAziendale.services;

import com.example.appGestioneAziendale.domain.dto.requests.ComuneRequest;
import com.example.appGestioneAziendale.domain.dto.requests.CreateDipendenteRequest;
import com.example.appGestioneAziendale.domain.dto.requests.EntityIdRequest;
import com.example.appGestioneAziendale.domain.entities.Comune;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Service
public class DatabaseSeederService {
    @Autowired
    private ComuneService comuneService;
    @Autowired
    private DipendenteService dipendenteService;

    public void createDatabase() {
        Faker faker = new Faker(Locale.ITALIAN);
        List<Long> comuniId = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ComuneRequest comuneRequest = new ComuneRequest(faker.gameOfThrones().city(), faker.elderScrolls().city());
            comuneService.createComune(comuneRequest);
            Comune createdComune = comuneService.getAll().get(i);
            comuniId.add(createdComune.getId());
        }
        for (int i = 0; i < 5; i++) {
            Long randomComuneId = comuniId.get(new Random().nextInt(comuniId.size()));
            EntityIdRequest entityIdRequest = new EntityIdRequest(randomComuneId);
            CreateDipendenteRequest createDipendenteRequest = new CreateDipendenteRequest(
                    faker.dragonBall().character(),
                    faker.funnyName().name(),
                    faker.internet().emailAddress(),
                    "1234",
                    entityIdRequest,
                    LocalDate.of(1990, 02, 25),
                    faker.phoneNumber().cellPhone(),
                    faker.avatar().image(),
                    "UTENTE"
            );
            dipendenteService.createDipendente(createDipendenteRequest);
        }
    }
}
