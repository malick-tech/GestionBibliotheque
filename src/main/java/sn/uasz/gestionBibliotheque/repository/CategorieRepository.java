package sn.uasz.gestionBibliotheque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.uasz.gestionBibliotheque.modele.Categorie;

import java.util.Optional;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie,Long> {
    Optional<Categorie> findByNameIgnoreCase(String name);
}
