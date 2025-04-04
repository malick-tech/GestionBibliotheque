package sn.uasz.gestionBibliotheque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.uasz.gestionBibliotheque.service.CategorieService;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {
    private final CategorieService categorieService;

    @Autowired
    public CategorieController (CategorieService categorieService){
        this.categorieService = categorieService;
    }
}
