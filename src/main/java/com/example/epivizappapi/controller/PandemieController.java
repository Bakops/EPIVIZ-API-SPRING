package com.example.epivizappapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.epivizappapi.model.Calendrier;
import com.example.epivizappapi.model.Pandemie;
import com.example.epivizappapi.repository.CalendrierRepository;
import com.example.epivizappapi.repository.PandemieRepository;

@RestController
@RequestMapping("/api/pandemie")
public class PandemieController {

    @Autowired
    private PandemieRepository pandemieRepository;

    @Autowired
    private CalendrierRepository calendrierRepository;

    @GetMapping
    public List<Pandemie> getAllPandemies() {
        return pandemieRepository.findAll();
    }

    @PostMapping
    public Pandemie createPandemie(@RequestBody Pandemie pandemie) {
        // Vérifiez si le calendrier existe
        if (pandemie.getCalendrier() == null || pandemie.getCalendrier().getId() == null) {
            throw new RuntimeException("Le calendrier associé est requis.");
        }

        Calendrier calendrier = calendrierRepository.findById(pandemie.getCalendrier().getId())
                .orElseThrow(() -> new RuntimeException("Le calendrier spécifié n'existe pas."));

        // Associez le calendrier à la pandémie
        pandemie.setCalendrier(calendrier);

        return pandemieRepository.save(pandemie);
    }

    @GetMapping("/{id}")
    public Pandemie getPandemieById(@PathVariable Long id) {
        return pandemieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pandémie introuvable"));
    }

    @DeleteMapping("/{id}")
    public void deletePandemie(@PathVariable Long id) {
        pandemieRepository.deleteById(id);
    }
}