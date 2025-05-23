package sn.uasz.gestionBibliotheque.modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Emprunts")
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

    @Column(nullable = false, name = "dateEmprunt")
    private LocalDate dateEmprunt;

    @Column(nullable = false, name = "dateEcheance")
    private LocalDate dateEcheance;

    @Column(name = "dateRetour")
    private LocalDate dateRetour;

    @Column(name = "estRetourne")
    private boolean estRetourne;
}
