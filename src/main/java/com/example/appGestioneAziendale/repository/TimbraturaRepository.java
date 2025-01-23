package com.example.appGestioneAziendale.repository;

import com.example.appGestioneAziendale.domain.entities.Timbratura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimbraturaRepository extends JpaRepository<Timbratura, Long> {
}
