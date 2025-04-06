package sn.uasz.gestionBibliotheque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.uasz.gestionBibliotheque.modele.Emprunts;
import sn.uasz.gestionBibliotheque.modele.Livre;
import sn.uasz.gestionBibliotheque.modele.Membre;
import sn.uasz.gestionBibliotheque.service.EmpruntService;
import sn.uasz.gestionBibliotheque.service.LivreService;
import sn.uasz.gestionBibliotheque.service.MembreService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/emprunts")
public class EmpruntController {

    private final EmpruntService empruntService;
    private final LivreService livreService;
    private final MembreService membreService;

    @Autowired
    public EmpruntController(EmpruntService empruntService, LivreService livreService, MembreService membreService) {
        this.empruntService = empruntService;
        this.livreService = livreService;
        this.membreService = membreService;
    }

    @GetMapping
    public ResponseEntity<List<Emprunts>> getAllLoans() {
        List<Emprunts> emprunts = empruntService.getAllLoans();
        return new ResponseEntity<>(emprunts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprunts> getLoanById(@PathVariable Long id) {
        Optional<Emprunts> emprunt = empruntService.getLoanById(id);
        return emprunt.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/membre/{membreId}")
    public ResponseEntity<List<Emprunts>> getLoansByMember(@PathVariable Long membreId) {
        Optional<Membre> membre = membreService.getMemberById(membreId);
        if (membre.isPresent()) {
            List<Emprunts> emprunts = empruntService.getLoansByMember(membre.get());
            return new ResponseEntity<>(emprunts, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/actifs")
    public ResponseEntity<List<Emprunts>> getActiveLoans() {
        List<Emprunts> emprunts = empruntService.getActiveLoans();
        return new ResponseEntity<>(emprunts, HttpStatus.OK);
    }

    @GetMapping("/retards")
    public ResponseEntity<List<Emprunts>> getOverdueLoans() {
        List<Emprunts> emprunts = empruntService.getOverdueLoans();
        return new ResponseEntity<>(emprunts, HttpStatus.OK);
    }

    @GetMapping("/actifs/membre/{membreId}")
    public ResponseEntity<List<Emprunts>> getActiveLoansByMember(@PathVariable Long membreId) {
        Optional<Membre> membre = membreService.getMemberById(membreId);
        if (membre.isPresent()) {
            List<Emprunts> emprunts = empruntService.getActiveLoansByMember(membre.get());
            return new ResponseEntity<>(emprunts, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/periode")
    public ResponseEntity<List<Emprunts>> getLoansByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate debut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        List<Emprunts> emprunts = empruntService.getLoansByDateRange(debut, fin);
        return new ResponseEntity<>(emprunts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createLoan(@RequestBody Map<String, Object> requestBody) {
        try {
            Long livreId = Long.parseLong(requestBody.get("livreId").toString());
            Long membreId = Long.parseLong(requestBody.get("membreId").toString());
            int loanDays = Integer.parseInt(requestBody.get("jours").toString());

            Optional<Livre> livre = Optional.ofNullable(livreService.getBookById(livreId));
            Optional<Membre> membre = membreService.getMemberById(membreId);

            if (livre.isPresent() && membre.isPresent()) {
                Emprunts nouvelEmprunt = empruntService.createLoan(livre.get(), membre.get(), loanDays);
                return new ResponseEntity<>(nouvelEmprunt, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Livre ou membre non trouvé", HttpStatus.NOT_FOUND);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/retour")
    public ResponseEntity<?> returnBook(@PathVariable Long id) {
        try {
            Emprunts empruntRetourne = empruntService.returnBook(id);
            return new ResponseEntity<>(empruntRetourne, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}