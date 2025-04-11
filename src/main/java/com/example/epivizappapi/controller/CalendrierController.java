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
import com.example.epivizappapi.repository.CalendrierRepository;

@RestController
@RequestMapping("/api/calendar")
public class CalendrierController {

    @Autowired
    private CalendrierRepository calendrierRepository;

    @GetMapping
    public List<Calendrier> getAllCalendriers() {
        return calendrierRepository.findAll();
    }

    @PostMapping
    public Calendrier createCalendrier(@RequestBody Calendrier calendrier) {
        return calendrierRepository.save(calendrier);
    }

    @GetMapping("/{id}")
    public Calendrier getCalendrierById(@PathVariable Long id) {
        return calendrierRepository.findById(id).orElseThrow(() -> new RuntimeException("Calendrier introuvable"));
    }

    @DeleteMapping("/{id}")
    public void deleteCalendrier(@PathVariable Long id) {
        calendrierRepository.deleteById(id);
    }
}