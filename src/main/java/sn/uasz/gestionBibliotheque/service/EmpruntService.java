package sn.uasz.gestionBibliotheque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.uasz.gestionBibliotheque.modele.Emprunts;
import sn.uasz.gestionBibliotheque.modele.Livre;
import sn.uasz.gestionBibliotheque.modele.Membre;
import sn.uasz.gestionBibliotheque.repository.EmpruntsRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmpruntService {
    private final EmpruntsRepository empruntRepository;
    private final LivreService livreService;

    @Autowired
    public EmpruntService(EmpruntsRepository loanRepository, EmpruntsRepository empruntRepository, LivreService livreService) {
        this.empruntRepository = empruntRepository;
        this.livreService = livreService;
    }

    public List<Emprunts> getAllLoans() {
        return empruntRepository.findAll();
    }

    public Optional<Emprunts> getLoanById(Long id) {
        return empruntRepository.findById(id);
    }

    public List<Emprunts> getLoansByMember(Membre membre) {
        return empruntRepository.findByMembre(membre);
    }

    public List<Emprunts> getActiveLoans() {
        return empruntRepository.findByEstRetourneFalse();
    }

    public List<Emprunts> getOverdueLoans() {
        return empruntRepository.findOverdueLoans();
    }

    public List<Emprunts> getActiveLoansByMember(Membre membre) {
        return empruntRepository.findByMembreAndEstRetourneFalse(membre);
    }

    @Transactional
    public Emprunts createLoan(Livre livre, Membre membre, int loanDays) {
        if (!livreService.isBookAvailable(livre.getId())) {
            throw new RuntimeException("Livre non disponible pour l'emprunt");
        }

        Emprunts emprunt = new Emprunts();
        emprunt.setLivre(livre);
        emprunt.setMembre(membre);
        emprunt.setDateEmprunt(LocalDate.now());
        emprunt.setDateEcheance(LocalDate.now().plusDays(loanDays));
        emprunt.setEstRetourne(false);

        livreService.decreaseAvailableQuantity(livre);

        return empruntRepository.save(emprunt);
    }

    @Transactional
    public Emprunts returnBook(Long empruntId) {
        Optional<Emprunts> loanOptional = empruntRepository.findById(empruntId);
        if (loanOptional.isPresent()) {
            Emprunts emprunts = loanOptional.get();
            if (!emprunts.isEstRetourne()) {
                emprunts.setDateRetour(LocalDate.now());
                emprunts.setEstRetourne(true);
                livreService.increaseAvailableQuantity(emprunts.getLivre());
                return empruntRepository.save(emprunts);
            }
            throw new RuntimeException("Ce livre a déjà été retourné");
        }
        throw new RuntimeException("Prêt non trouvé");
    }

    public List<Emprunts> getLoansByDateRange(LocalDate startDate, LocalDate endDate) {
        return empruntRepository.findByEstRetourneFalse();
    }
}
