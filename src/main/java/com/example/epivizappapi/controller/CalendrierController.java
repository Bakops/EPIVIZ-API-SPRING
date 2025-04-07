package com.example.epivizappapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.epivizappapi.model.Calendrier;
import com.example.epivizappapi.repository.CalendrierRepository;

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
