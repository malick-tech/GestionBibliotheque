package sn.uasz.gestionBibliotheque.modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Livre")
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private String auteur;

    @Column(unique = true)
    private String isbn;

    private String editeur;

    private int annee_publication;

    private int quantite;

    private int quantite_disponible;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    @OneToMany(mappedBy = "livre")
    private Set<Emprunts> emprunts = new HashSet<>();
}
