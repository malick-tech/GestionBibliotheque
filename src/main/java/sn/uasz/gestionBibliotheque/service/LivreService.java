package sn.uasz.gestionBibliotheque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.uasz.gestionBibliotheque.modele.Categorie;
import sn.uasz.gestionBibliotheque.modele.Livre;
import sn.uasz.gestionBibliotheque.repository.LivreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LivreService {
    private final LivreRepository livreRepository;

    @Autowired
    public LivreService(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    public List<Livre> getAllBooks() {
        return livreRepository.findAll();
    }

    public Livre getBookById(Long id) {
        return livreRepository.findById(id).get();
    }

    public Optional<Livre> getBookByIsbn(String isbn) {
        return livreRepository.findByIsbn(isbn);
    }

    public List<Livre> getBooksByTitle(String title) {
        return livreRepository.findByTitle(title);
    }

    public List<Livre> getBooksByAuthor(String author) {
        return livreRepository.findByAuthor(author);
    }

    public List<Livre> getBooksByCategory(Categorie categorie) {
        return livreRepository.findByCategory(categorie);
    }

    public List<Livre> getAvailableBooks() {
        return livreRepository.findAvailableBooks();
    }

    public Livre saveBook(Livre livre) {
        return livreRepository.save(livre);
    }

    public void deleteBook(Long id) {
        livreRepository.deleteById(id);
    }

    public boolean isBookAvailable(Long livreId) {
        Optional<Livre> bookOpt = livreRepository.findById(livreId);
        return bookOpt.filter(livre -> livre.getQuantiteDisponible() > 0).isPresent();
    }

    public Livre decreaseAvailableQuantity(Livre livre) {
        if (livre.getQuantiteDisponible() > 0) {
            livre.setQuantiteDisponible(livre.getQuantiteDisponible() - 1);
            return livreRepository.save(livre);
        }
        throw new RuntimeException("Le livre n'est pas disponible");
    }

    public Livre increaseAvailableQuantity(Livre livre) {
        if (livre.getQuantiteDisponible() < livre.getQuantite()) {
            livre.setQuantiteDisponible(livre.getQuantiteDisponible() + 1);
            return livreRepository.save(livre);
        }
        throw new RuntimeException("Erreur: la quantité disponible ne peut pas dépasser la quantité totale");
    }
}
