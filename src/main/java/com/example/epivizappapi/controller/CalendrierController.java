package com.example.epivizappapi.controller;

import com.example.epivizappapi.model.Calendrier;
import com.example.epivizappapi.repository.CalendrierRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendriers")
public class CalendrierController {
    private final CalendrierRepository repository;

    public CalendrierController(CalendrierRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Calendrier> getAllCalendriers() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Calendrier getCalendrierById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @PostMapping
    public Calendrier createCalendrier(@RequestBody Calendrier calendrier) {
        return repository.save(calendrier);
    }

    @DeleteMapping("/{id}")
    public void deleteCalendrier(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
