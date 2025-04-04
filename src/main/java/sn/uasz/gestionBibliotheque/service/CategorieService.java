package sn.uasz.gestionBibliotheque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.uasz.gestionBibliotheque.modele.Categorie;
import sn.uasz.gestionBibliotheque.repository.CategorieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {
    private final CategorieRepository categorieRepository;

    @Autowired
    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    public Optional<Categorie> getCategoryById(Long id) {
        return categorieRepository.findById(id);
    }

    public Optional<Categorie> getCategoryByName(String name) {
        return categorieRepository.findByNameIgnoreCase(name);
    }

    public Categorie saveCategory(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public void deleteCategory(Long id) {
        categorieRepository.deleteById(id);
    }
}
