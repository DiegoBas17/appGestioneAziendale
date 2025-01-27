package com.example.appGestioneAziendale.mappers;

import com.example.appGestioneAziendale.domain.dto.requests.TimbraturaRequest;
import com.example.appGestioneAziendale.domain.entities.Dipendente;
import com.example.appGestioneAziendale.domain.entities.Timbratura;
import com.example.appGestioneAziendale.domain.exceptions.MyEntityNotFoundException;
import com.example.appGestioneAziendale.services.DipendenteService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class TimbraturaMapper {
    private DipendenteService dipendenteService;

    public Timbratura fromTimbraturaRequest(TimbraturaRequest request){
        return Timbratura.builder()
                .ingresso(LocalDateTime.now())
                .uscita(request.uscita())
                .inizioPausaPranzo(request.inizioPausa())
                .finePausaPranzo(request.finePausa())
                .idDipendente((Dipendente) request.idDipendente()
                        .stream()
                        .map(id ->{
                            try{
                                return dipendenteService.getById(id.getId());
                            }catch (MyEntityNotFoundException e){
                                throw new RuntimeException("Dipendente non trovato");
                            }
                        })
                        .collect(Collectors.toSet()))
                .build();
    }
}
