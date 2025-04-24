package com.example.epivizappapi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.epivizappapi.model.Localisation;
import com.example.epivizappapi.repository.LocalisationRepository;

@RestController
@RequestMapping("/api/location")
public class LocalisationController {

    @Autowired
    private LocalisationRepository localisationRepository;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllLocations() {
        try {
            List<Localisation> locations = localisationRepository.findAll();
            List<Map<String, Object>> simplifiedLocations = new ArrayList<>();

            for (Localisation loc : locations) {
                Map<String, Object> item = new HashMap<>();
                item.put("id", loc.getId());
                item.put("country", loc.getCountry());
                item.put("continent", loc.getContinent());
                item.put("latitude", loc.getLatitude());
                item.put("longitude", loc.getLongitude());
                // Ne pas inclure les données qui créent des références circulaires
                simplifiedLocations.add(item);
            }

            return ResponseEntity.ok(simplifiedLocations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public Localisation createLocalisation(@RequestBody Localisation localisation) {
        return localisationRepository.save(localisation);
    }

    @GetMapping("/{id}")
    public Localisation getLocalisationById(@PathVariable Long id) {
        return localisationRepository.findById(id).orElseThrow(() -> new RuntimeException("Localisation introuvable"));
    }

    @DeleteMapping("/{id}")
    public void deleteLocalisation(@PathVariable Long id) {
        localisationRepository.deleteById(id);
    }
}