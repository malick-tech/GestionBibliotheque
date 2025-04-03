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
@Table(name = "Livre")
public class Livre {
    private Long id;
    private String titre;
    private String auteurs;
    private int anneePublication;
    private String domaine;
    private String niveau;
}
