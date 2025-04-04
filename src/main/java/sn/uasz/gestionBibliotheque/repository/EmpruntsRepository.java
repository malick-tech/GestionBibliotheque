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
    List<Emprunts> findByMember(Membre membre);

    List<Emprunts> findByBook(Livre livre);

    List<Emprunts> findByIsReturnedFalse();

    @Query("SELECT e FROM Emprunts e WHERE e.estRetourne = false AND l.dueDate < CURRENT_DATE")
    List<Emprunts> findOverdueLoans();

    List<Emprunts> findByMemberAndIsReturnedFalse(Membre membre);

    List<Emprunts> findByLoanDateBetween(LocalDate startDate, LocalDate endDate);
}
