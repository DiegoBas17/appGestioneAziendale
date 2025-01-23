package com.example.appGestioneAziendale.repository;

import com.example.appGestioneAziendale.domain.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
