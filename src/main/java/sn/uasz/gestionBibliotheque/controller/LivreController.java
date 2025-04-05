package sn.uasz.gestionBibliotheque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.uasz.gestionBibliotheque.modele.Categorie;
import sn.uasz.gestionBibliotheque.modele.Livre;
import sn.uasz.gestionBibliotheque.service.CategorieService;
import sn.uasz.gestionBibliotheque.service.LivreService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/livres")
public class LivreController {
    private final LivreService livreService;
    private final CategorieService categorieService;

    @Autowired
    public LivreController(LivreService bookService, CategorieService categoryService) {
        this.livreService = bookService;
        this.categorieService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Livre>> getAllBooks() {
        return ResponseEntity.ok(livreService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livre> getBookById(@PathVariable("id") Long id) {
        Optional<Livre> livre = Optional.ofNullable(livreService.getBookById(id));
        return livre.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Livre> getBookByIsbn(@PathVariable("isbn") String isbn) {
        Optional<Livre> livre = livreService.getBookByIsbn(isbn);
        return livre.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Livre>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Long categoryId) {

        if (title != null && !title.isEmpty()) {
            return ResponseEntity.ok(livreService.getBooksByTitle(title));
        } else if (author != null && !author.isEmpty()) {
            return ResponseEntity.ok(livreService.getBooksByAuthor(author));
        } else if (categoryId != null) {
            Optional<Categorie> category = categorieService.getCategoryById(categoryId);
            if (category.isPresent()) {
                return ResponseEntity.ok(livreService.getBooksByCategory(category.get()));
            }
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(livreService.getAllBooks());
        }
    }

    @GetMapping("/available")
    public ResponseEntity<List<Livre>> getAvailableBooks() {
        return ResponseEntity.ok(livreService.getAvailableBooks());
    }

    @PostMapping
    public ResponseEntity<Livre> createBook(@RequestBody Livre livre) {
        // Vérifie si la catégorie existe
        if (livre.getCategorie()!= null && livre.getCategorie().getId() != null) {
            Optional<Categorie> categorie = categorieService.getCategoryById(livre.getCategorie().getId());
            if (categorie.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            livre.setCategorie(categorie.get());
        }

        // Initialise la quantité disponible à la quantité totale pour un nouveau livre
        if (livre.getId() == null) {
            livre.setQuantiteDisponible(livre.getQuantite());
        }

        Livre savedBook = livreService.saveBook(livre);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livre> updateBook(@PathVariable("id") Long id, @RequestBody Livre livre) {
        Optional<Livre> existingBook = Optional.ofNullable(livreService.getBookById(id));
        if (existingBook.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Vérifie si la catégorie existe
        if (livre.getCategorie() != null && livre.getCategorie().getId() != null) {
            Optional<Categorie> category = categorieService.getCategoryById(livre.getCategorie().getId());
            if (category.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            livre.setCategorie(category.get());
        }

        // Préserve la cohérence des quantités
        Livre currentBook = existingBook.get();
        int qtyDiff = livre.getQuantite() - currentBook.getQuantite();
        livre.setQuantiteDisponible(currentBook.getQuantiteDisponible() + qtyDiff);

        livre.setId(id);
        Livre updatedBook = livreService.saveBook(livre);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
        Optional<Livre> book = Optional.ofNullable(livreService.getBookById(id));
        if (book.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        livreService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
