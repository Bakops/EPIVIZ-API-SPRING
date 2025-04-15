package com.example.epivizappapi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public List<Map<String, Object>> getAllPandemies() {
        List<Pandemie> pandemies = pandemieRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Pandemie pandemie : pandemies) {
            Map<String, Object> pandemieMap = new HashMap<>();
            pandemieMap.put("id", pandemie.getId());
            pandemieMap.put("type", pandemie.getType());
        
            pandemieMap.put("calendrier", null);

            result.add(pandemieMap);
        }

        return result;
    }

    @PostMapping
    public ResponseEntity<?> createPandemie(@RequestBody Pandemie pandemie) {
        try {
            if (pandemie.getCalendrier() != null && pandemie.getCalendrier().getId() != null) {
                Optional<Calendrier> optionalCalendrier = calendrierRepository.findById(pandemie.getCalendrier().getId());
                if (!optionalCalendrier.isPresent()) {
                    return ResponseEntity.badRequest().body("Calendrier avec l'ID " + pandemie.getCalendrier().getId() + " n'existe pas.");
                }
                pandemie.setCalendrier(optionalCalendrier.get());
            } else {
                pandemie.setCalendrier(null);
            }

            Pandemie savedPandemie = pandemieRepository.save(pandemie);

            Map<String, Object> response = new HashMap<>();
            response.put("id", savedPandemie.getId());
            response.put("type", savedPandemie.getType());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de la création de la pandémie: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPandemieById(@PathVariable Long id) {
        try {
            Optional<Pandemie> optionalPandemie = pandemieRepository.findById(id);
            if (!optionalPandemie.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            Pandemie pandemie = optionalPandemie.get();
            Map<String, Object> pandemieMap = new HashMap<>();
            pandemieMap.put("id", pandemie.getId());
            pandemieMap.put("type", pandemie.getType());
            pandemieMap.put("calendrier", null);

            return ResponseEntity.ok(pandemieMap);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de la récupération de la pandémie: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePandemie(@PathVariable Long id) {
        try {
            if (!pandemieRepository.existsById(id)) {
                return ResponseEntity.notFound().build();
            }

            pandemieRepository.deleteById(id);
            return ResponseEntity.ok().body("Pandémie supprimée avec succès");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de la suppression de la pandémie: " + e.getMessage());
        }
    }
}
