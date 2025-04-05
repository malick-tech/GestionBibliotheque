package sn.uasz.gestionBibliotheque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.uasz.gestionBibliotheque.modele.Membre;
import sn.uasz.gestionBibliotheque.repository.MembreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MembreService {
    private final MembreRepository membreRepository;

    @Autowired
    public MembreService(MembreRepository membreRepository) {
        this.membreRepository = membreRepository;
    }

    public List<Membre> getAllMembers() {
        return membreRepository.findAll();
    }

    public Optional<Membre> getMemberById(Long id) {
        return membreRepository.findById(id);
    }

    public Optional<Membre> getMemberByEmail(String email) {
        return membreRepository.rechercheParEmail(email);
    }

    public List<Membre> getMembersByName(String searchTerm) {
        return membreRepository.rechercheParPrenomOuNom(searchTerm, searchTerm);
    }

    public Membre saveMember(Membre member) {
        return membreRepository.save(member);
    }

    public void deleteMember(Long id) {
        membreRepository.deleteById(id);
    }
}
