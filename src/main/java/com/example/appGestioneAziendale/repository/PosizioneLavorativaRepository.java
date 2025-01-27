package com.example.appGestioneAziendale.repository;

import com.example.appGestioneAziendale.domain.entities.Commento;
import com.example.appGestioneAziendale.domain.entities.PosizioneLavorativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosizioneLavorativaRepository extends JpaRepository<PosizioneLavorativa, Long> {
}
