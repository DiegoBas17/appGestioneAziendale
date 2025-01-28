package com.example.appGestioneAziendale.mappers;

import com.example.appGestioneAziendale.domain.dto.requests.CreateDipendenteRequest;
import com.example.appGestioneAziendale.domain.entities.Dipendente;
import com.example.appGestioneAziendale.domain.entities.PosizioneLavorativa;
import com.example.appGestioneAziendale.domain.enums.Ruolo;
import com.example.appGestioneAziendale.domain.exceptions.MyIllegalException;
import com.example.appGestioneAziendale.repository.PosizioneLavorativaRepository;
import com.example.appGestioneAziendale.services.ComuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DipendenteMapper {
    @Autowired
    private ComuneService comuneService;

    @Autowired
    private PosizioneLavorativaRepository posizioneLavorativaRepository;

    public Dipendente fromCreateDipendenteRequest(CreateDipendenteRequest request) {
        PosizioneLavorativa posizioneLavorativa = posizioneLavorativaRepository
                .findById(request.idPosizioneLavorativa())
                .orElseThrow(() -> new IllegalArgumentException
                        ("Posizione lavorativa con ID " + request.idPosizioneLavorativa()+ "non trovato"));

        Ruolo ruolo;
        try {
            ruolo = Ruolo.valueOf(request.ruolo().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new MyIllegalException("Ruolo inserito non valido!");
        }
        return Dipendente.builder()
                .nome(request.nome())
                .cognome(request.cognome())
                .email(request.email())
                .password(request.password())
                .comuneDiNascita(comuneService.getById(request.comune_di_nascita().id()))
                .dataDiNascita(request.data_di_nascita())
                .telefono(request.telefono())
                .avatar(request.avatar())
                .ruolo(ruolo)
                .idPosizioneLavorativa(posizioneLavorativa)
                .build();
    }
}
