package com.example.epivizappapi.controller;

import java.util.List;

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
@RequestMapping("/api/localisations")
public class LocalisationController {
    private final LocalisationRepository repository;

    public LocalisationController(LocalisationRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Localisation> getAllLocalisations() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Localisation getLocalisationById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @PostMapping
    public Localisation createLocalisation(@RequestBody Localisation localisation) {
        return repository.save(localisation);
    }

    @DeleteMapping("/{id}")
    public void deleteLocalisation(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
