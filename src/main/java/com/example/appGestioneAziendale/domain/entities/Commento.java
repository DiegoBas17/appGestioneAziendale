package com.example.appGestioneAziendale.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Commento")
@EntityListeners(AuditingEntityListener.class)
public class Commento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String testo;
    @ManyToOne
    @JoinColumn(name = "id_news", referencedColumnName = "id")
    private News news;
    @ManyToOne
    @JoinColumn(name = "id_dipendente", referencedColumnName = "id")
    private Dipendente dipendente;
}
