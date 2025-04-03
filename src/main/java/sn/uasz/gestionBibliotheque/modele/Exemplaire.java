package sn.uasz.gestionBibliotheque.modele;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Exemplaire")
public class Exemplaire {
    private Long numero;
    private String referenceLivre;
}
