package com.example.appGestioneAziendale.mappers;

import com.example.appGestioneAziendale.domain.dto.requests.TimbraturaRequest;
import com.example.appGestioneAziendale.domain.entities.Timbratura;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimbraturaMapper {

    public Timbratura fromTimbraturaRequest(TimbraturaRequest request){
        return Timbratura.builder()
                .ingresso(LocalDateTime.now())
                .uscita(request.uscita())
                .inizioPausaPranzo(request.inizioPausa())
                .finePausaPranzo(request.finePausa())
                .idDipendente(request.idDipendente())
                .build();
    }
}
