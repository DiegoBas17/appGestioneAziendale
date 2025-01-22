package com.example.appGestioneAziendale.mappers;

import com.example.appGestioneAziendale.services.ComuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DipendenteMapper {
    @Autowired
    private ComuneService comuneService;

}
