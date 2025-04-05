package sn.uasz.gestionBibliotheque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sn.uasz.gestionBibliotheque.modele.Categorie;
import sn.uasz.gestionBibliotheque.modele.Livre;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivreRepository extends JpaRepository<Livre,Long> {
    List<Livre> findByTitre(String titre);

    List<Livre> findByAuteur(String auteur);

    Optional<Livre> findByIsbn(String isbn);

    List<Livre> findByCategorie(Categorie category);

    @Query("SELECT l FROM Livre l WHERE b.quantite_disponible > 0")
    List<Livre> findLivreDisponible();
}
