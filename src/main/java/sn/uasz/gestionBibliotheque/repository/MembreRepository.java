package sn.uasz.gestionBibliotheque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sn.uasz.gestionBibliotheque.modele.Membre;

import java.util.List;
import java.util.Optional;

@Repository
public interface MembreRepository extends JpaRepository<Membre,Long> {
    @Query("SELECT m FROM Membre m WHERE m.nom LIKE %:nom%")
    List<Membre> rechercheParNom(String nom);

    List<Membre> findByPrenomOrNom(String prenom, String nom);

    Optional<Membre> findByEmail(String email);
}
