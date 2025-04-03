package sn.uasz.gestionBibliotheque.modele;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Abonnee")
public class Abonnee {
    private Long numeroAbonnement;
    private String prenom;
    private String nom;
    private String status;
    private String institutionRattachement;
    private String email;
}
