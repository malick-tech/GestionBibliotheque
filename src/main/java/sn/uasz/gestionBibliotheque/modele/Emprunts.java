package sn.uasz.gestionBibliotheque.modele;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Emprunts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "livre_id", nullable = false)
    private Livre livre;

    @ManyToOne
    @JoinColumn(name = "membre_id", nullable = false)
    private Membre membre;

    @Column(nullable = false)
    private LocalDate dateEmprunt;

    @Column(nullable = false)
    private LocalDate dueDate;

    private LocalDate dateRetour;

    private boolean estRetourne;
}
