package sn.uasz.gestionBibliotheque.modele;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Bibliothecaire")
public class Bibliothecaire {
    private Long id;
    private String prenom;
    private String nom;
    private Date dateRecrutement;
    private String adresse;
    private String email;
}
