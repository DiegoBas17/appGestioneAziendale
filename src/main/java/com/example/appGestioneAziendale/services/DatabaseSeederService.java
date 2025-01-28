package com.example.appGestioneAziendale.services;

import com.example.appGestioneAziendale.domain.dto.requests.*;
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
    @Autowired
    private DipartimentoService dipartimentoService;
    @Autowired
    private PosizioneLavorativaService posizioneLavorativaService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private CommentoService commentoService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private ComunicazioneAziendaleService comunicazioneAziendaleService;

    public void createDatabase() {
        Faker faker = new Faker(Locale.ITALIAN);
        List<Long> comuniId = new ArrayList<>();
        List<Long> dipartimentiId = new ArrayList<>();
        List<Long> posizioniLavorativeId = new ArrayList<>();
        List<Long> dipendentiId = new ArrayList<>();
        List<Long> newsId = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ComuneRequest comuneRequest = new ComuneRequest(faker.gameOfThrones().city(), faker.elderScrolls().city());
            comuniId.add(comuneService.createComune(comuneRequest).id());
        }
        for (int i = 0; i < 3; i++) {
            DipartimentoRequest dipartimentoRequest = new DipartimentoRequest(faker.pokemon().location(), faker.leagueOfLegends().champion());
            dipartimentiId.add(dipartimentoService.createDipartimento(dipartimentoRequest).id());
        }
        for (int i = 0; i < 3; i++) {
            Long randomDipartimentoId = dipartimentiId.get(new Random().nextInt(dipartimentiId.size()));
            PosizioneLavorativaRequest posizioneLavorativaRequest = new PosizioneLavorativaRequest(faker.company().profession(), faker.chuckNorris().fact(), randomDipartimentoId);
            posizioniLavorativeId.add(posizioneLavorativaService.createPosizioneLavorativa(posizioneLavorativaRequest).id());
        }
        for (int i = 0; i < 5; i++) {
            Long randomComuneId = comuniId.get(new Random().nextInt(comuniId.size()));
            Long randomPosizioneLavorativaId = posizioniLavorativeId.get(new Random().nextInt(posizioniLavorativeId.size()));
            CreateDipendenteRequest createDipendenteRequest = new CreateDipendenteRequest(
                    faker.dragonBall().character(),
                    faker.funnyName().name(),
                    faker.internet().emailAddress(),
                    "1234",
                    new EntityIdRequest(randomComuneId),
                    LocalDate.of(1990, 02, 25),
                    faker.phoneNumber().cellPhone(),
                    faker.avatar().image(),
                    "UTENTE",
                    randomPosizioneLavorativaId
            );
            dipendentiId.add(dipendenteService.createDipendente(createDipendenteRequest).id());
        }
        for (int i = 0; i < 3; i++) {
            Long randomPublisherId = dipendentiId.get(new Random().nextInt(dipendentiId.size()));
            CreateNewsRequest createNewsRequest = new CreateNewsRequest(faker.book().title(), faker.chuckNorris().fact(), faker.internet().image(), faker.internet().image(), randomPublisherId);
            newsId.add(newsService.createNews(createNewsRequest).id());
        }
        for (int i = 0; i < 3; i++) {
            Long randomNewsId = newsId.get(new Random().nextInt(newsId.size()));
            Long randomPublisherId = dipendentiId.get(new Random().nextInt(dipendentiId.size()));
            CreateCommentoRequest createCommentoRequest = new CreateCommentoRequest(faker.chuckNorris().fact(), new EntityIdRequest(randomNewsId), new EntityIdRequest(randomPublisherId));
            commentoService.createCommento(createCommentoRequest);
        }
        for (int i = 0; i < newsId.size(); i++) {
            Long randomPublisherId = dipendentiId.get(new Random().nextInt(dipendentiId.size()));
            likeService.createLike(new LikeRequest(newsId.get(i), randomPublisherId));
        }
        for (int i = 0; i < 3; i++) {
            Long randomPublisherId = dipendentiId.get(new Random().nextInt(dipendentiId.size()));
            ComunicazioneAziendaleRequest comunicazioneAziendaleRequest = new ComunicazioneAziendaleRequest(faker.pokemon().name(), faker.book().title(), faker.file().fileName());
            comunicazioneAziendaleService.createComunicazioneAziendale(randomPublisherId, comunicazioneAziendaleRequest);
        }
    }
}
