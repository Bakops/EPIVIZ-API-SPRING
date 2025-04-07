package com.example.epivizappapi.controller;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.epivizappapi.model.Pandemie;
import com.example.epivizappapi.repository.PandemieRepository;

@RestController
@RequestMapping("/api/pandemies")
public class PandemieController {
    private final PandemieRepository repository;

    public PandemieController(PandemieRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Pandemie> getAllPandemies() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Pandemie getPandemieById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @PostMapping
    public Pandemie createPandemie(@RequestBody Pandemie pandemie) {
        return repository.save(pandemie);
    }

    @DeleteMapping("/{id}")
    public void deletePandemie(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
