package sn.uasz.gestionBibliotheque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.uasz.gestionBibliotheque.service.EmpruntService;

@RestController
@RequestMapping("/api/emprunts")
public class EmpruntController {
    private final EmpruntService empruntService;

    @Autowired
    public EmpruntController(EmpruntService empruntService){
        this.empruntService = empruntService;
    }
}
