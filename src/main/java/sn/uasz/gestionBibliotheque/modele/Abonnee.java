package sn.uasz.gestionBibliotheque.modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Abonnee")
public class Abonnee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroAbonnement;
    private String prenom;
    private String nom;
    private String status;
    private String institutionRattachement;
    private String email;
}
