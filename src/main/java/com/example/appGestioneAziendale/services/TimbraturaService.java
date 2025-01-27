package com.example.appGestioneAziendale.services;

import com.example.appGestioneAziendale.domain.dto.requests.TimbraturaRequest;
import com.example.appGestioneAziendale.domain.dto.response.EntityIdResponse;
import com.example.appGestioneAziendale.domain.entities.Timbratura;
import com.example.appGestioneAziendale.domain.exceptions.MyEntityNotFoundException;
import com.example.appGestioneAziendale.mappers.TimbraturaMapper;
import com.example.appGestioneAziendale.repository.TimbraturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimbraturaService {
    @Autowired
    private TimbraturaRepository timbraturaRepository;
    @Autowired
    private TimbraturaMapper timbraturaMapper;

    public Timbratura getById(Long id) throws MyEntityNotFoundException{
        return timbraturaRepository.findById(id).orElseThrow(()-> new MyEntityNotFoundException("la timbratura con id " +id + " non esiste"));
    }

    public List<Timbratura> findAll(){
        return timbraturaRepository.findAll();
    }

    public EntityIdResponse createTimbratura(TimbraturaRequest request){
        Timbratura timbratura = timbraturaMapper.fromTimbraturaRequest(request);
        return EntityIdResponse.builder()
                .id(timbraturaRepository.save(timbratura).getId())
                .build();
    }
}
