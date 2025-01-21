package com.example.appGestioneAziendale.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "timbratura")
@EntityListeners(AuditingEntityListener.class)
public class Timbratura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime ingresso;
    @Column(nullable = false)
    private LocalDateTime uscita;
    @Column(nullable = false, name = "inizio_pausa_pranzo")
    private LocalDateTime inizioPausaPranzo;
    @Column(nullable = false, name = "fine_pausa_pranzo")
    private LocalDateTime finePausaPranzo;

    //private Dipendente id_dipendente;
}
