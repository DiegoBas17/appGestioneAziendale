package com.example.appGestioneAziendale.repository;

import com.example.appGestioneAziendale.domain.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
}
