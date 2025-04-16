package com.example.epivizappapi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            pandemieMap.put("nom_pandemie", pandemie.getNom());
            pandemieMap.put("data", pandemie.getData());
            
            if (pandemie.getCalendrier() != null && pandemie.getCalendrier().getId() != null) {
                Map<String, Object> calendrierMap = new HashMap<>();
                calendrierMap.put("id", pandemie.getCalendrier().getId());
                pandemieMap.put("calendrier", calendrierMap);
            } else {
                pandemieMap.put("calendrier", null);
            }
            
            result.add(pandemieMap);
        }
        
        System.out.println("Pandémies récupérées: " + result);  
        return result;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createPandemie(@RequestBody Pandemie pandemie) {
        try {
            if (pandemie.getCalendrier() != null && pandemie.getCalendrier().getId() != null) {
                Calendrier calendrier = calendrierRepository.findById(pandemie.getCalendrier().getId())
                        .orElse(null);
                pandemie.setCalendrier(calendrier);
            } else {
                pandemie.setCalendrier(null);
            }
            
            Pandemie savedPandemie = pandemieRepository.save(pandemie);
            
            Map<String, Object> response = new HashMap<>();
            response.put("id", savedPandemie.getId());
            response.put("nom", savedPandemie.getNom());
            response.put("data", savedPandemie.getData());
            
            if (savedPandemie.getCalendrier() != null) {
                Map<String, Object> calendrierMap = new HashMap<>();
                calendrierMap.put("id", savedPandemie.getCalendrier().getId());
                response.put("calendrier", calendrierMap);
            } else {
                response.put("calendrier", null);
            }
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la création de la pandémie: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPandemieById(@PathVariable Long id) {
        try {
            Pandemie pandemie = pandemieRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Pandémie introuvable avec l'ID: " + id));
            
            Map<String, Object> pandemieMap = new HashMap<>();
            pandemieMap.put("id", pandemie.getId());
            pandemieMap.put("nom", pandemie.getNom());
            pandemieMap.put("data", pandemie.getData());
            
            if (pandemie.getCalendrier() != null) {
                Map<String, Object> calendrierMap = new HashMap<>();
                calendrierMap.put("id", pandemie.getCalendrier().getId());
                pandemieMap.put("calendrier", calendrierMap);
            } else {
                pandemieMap.put("calendrier", null);
            }
            
            return ResponseEntity.ok(pandemieMap);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la récupération de la pandémie: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePandemie(@PathVariable Long id) {
        try {
            if (!pandemieRepository.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            
            pandemieRepository.deleteById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Pandémie supprimée avec succès");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la suppression de la pandémie: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
    
    @GetMapping("/noms")
    public List<Map<String, Object>> getPandemieNoms() {
        List<Pandemie> pandemies = pandemieRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();
        
        for (Pandemie pandemie : pandemies) {
            Map<String, Object> pandemieMap = new HashMap<>();
            pandemieMap.put("id", pandemie.getId());
            pandemieMap.put("nom", pandemie.getNom());
            result.add(pandemieMap);
        }
        
        return result;
    }
}
