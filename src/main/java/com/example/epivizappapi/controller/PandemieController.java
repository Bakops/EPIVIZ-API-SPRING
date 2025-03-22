package com.example.epivizappapi.controller;
import com.example.epivizappapi.model.Pandemie;
import com.example.epivizappapi.repository.PandemieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
