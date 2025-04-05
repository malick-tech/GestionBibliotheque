package sn.uasz.gestionBibliotheque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sn.uasz.gestionBibliotheque.modele.Emprunts;
import sn.uasz.gestionBibliotheque.modele.Livre;
import sn.uasz.gestionBibliotheque.modele.Membre;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmpruntsRepository extends JpaRepository<Emprunts,Long> {

    // 1. Recherche par membre
    List<Emprunts> findByMembre(Membre membre);

    // 2. Recherche par livre
    List<Emprunts> findByLivre(Livre livre);

    // 3. Emprunts non retournés
    List<Emprunts> findByEstRetourneFalse();

    // 4. Emprunts en retard (requête JPQL corrigée)
    @Query("SELECT e FROM Emprunts e WHERE e.estRetourne = false AND e.dateEcheance < CURRENT_DATE")
    List<Emprunts> findOverdueLoans();

    // 5. Emprunts non retournés pour un membre
    List<Emprunts> findByMembreAndEstRetourneFalse(Membre membre);

    // 6. Emprunts entre deux dates
    List<Emprunts> findByDateEmpruntBetween(LocalDate startDate, LocalDate endDate);
}
