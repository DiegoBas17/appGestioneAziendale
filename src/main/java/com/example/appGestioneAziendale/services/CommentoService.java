package com.example.appGestioneAziendale.services;

import com.example.appGestioneAziendale.repository.CommentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentoService {
    @Autowired
    private CommentoRepository commentoRepository;


}
