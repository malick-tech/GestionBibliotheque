package sn.uasz.gestionBibliotheque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.uasz.gestionBibliotheque.modele.Membre;

import java.util.List;
import java.util.Optional;

@Repository
public interface MembreRepository extends JpaRepository<Membre,Long> {
    List<Membre> rechercheParNom(String nom);

    List<Membre> rechercheParPrenomOuNom(String prenom, String nom);

    Optional<Membre> rechercheParEmail(String email);
}
