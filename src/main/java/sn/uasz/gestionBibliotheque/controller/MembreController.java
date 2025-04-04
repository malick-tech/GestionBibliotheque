package sn.uasz.gestionBibliotheque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.uasz.gestionBibliotheque.modele.Membre;
import sn.uasz.gestionBibliotheque.service.MembreService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/membres")
public class MembreController {
    private final MembreService membreService;

    @Autowired
    public MembreController(MembreService memberService) {
        this.membreService = memberService;
    }

    @GetMapping
    public ResponseEntity<List<Membre>> getAllMembers() {
        return ResponseEntity.ok(membreService.getAllMembers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Membre> getMemberById(@PathVariable("id") Long id) {
        Optional<Membre> member = membreService.getMemberById(id);
        return member.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Membre> getMemberByEmail(@PathVariable("email") String email) {
        Optional<Membre> member = membreService.getMemberByEmail(email);
        return member.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Membre>> searchMembers(@RequestParam String query) {
        return ResponseEntity.ok(membreService.getMembersByName(query));
    }

    @PostMapping
    public ResponseEntity<Membre> createMember(@RequestBody Membre membre) {
        // Vérifie si l'email existe déjà
        Optional<Membre> existingMember = membreService.getMemberByEmail(membre.getEmail());
        if (existingMember.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Membre savedMember = membreService.saveMember(membre);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMember);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Membre> updateMember(@PathVariable("id") Long id, @RequestBody Membre member) {
        Optional<Membre> existingMember = membreService.getMemberById(id);
        if (existingMember.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Vérifie si l'email existe déjà pour un autre membre
        Optional<Membre> memberWithEmail = membreService.getMemberByEmail(member.getEmail());
        if (memberWithEmail.isPresent() && !memberWithEmail.get().getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        member.setId(id);
        Membre updatedMember = membreService.saveMember(member);
        return ResponseEntity.ok(updatedMember);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable("id") Long id) {
        Optional<Membre> member = membreService.getMemberById(id);
        if (member.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        membreService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
