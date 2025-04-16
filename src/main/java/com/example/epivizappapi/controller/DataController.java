package com.example.epivizappapi.controller;

import java.util.List;

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

import com.example.epivizappapi.model.Calendrier;
import com.example.epivizappapi.model.Data;
import com.example.epivizappapi.repository.CalendrierRepository;
import com.example.epivizappapi.repository.DataRepository;
import com.example.epivizappapi.services.DataService;

@RestController
@RequestMapping("/api/data")
public class DataController {

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private CalendrierRepository calendrierRepository;

    @Autowired
    private DataService dataService;

    @GetMapping
    public List<Data> getAllData() {
        return dataRepository.findAllWithValidCalendrier();
    }

    @GetMapping("/{id}")
    public Data getDataById(@PathVariable Long id) {
        return dataRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Donnée introuvable"));
    }

    @DeleteMapping("/{id}")
    public void deleteData(@PathVariable Long id) {
        dataRepository.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<Data> createData(@RequestBody Data data) {
        try {
            if (data.getCalendrier() == null || data.getCalendrier().getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(null);
            }

            Long calendrierId = data.getCalendrier().getId();
            Calendrier calendrierExistant = calendrierRepository.findById(calendrierId)
                .orElseThrow(() -> new RuntimeException("Le calendrier avec l'id " + calendrierId + " n'existe pas"));

            data.setCalendrier(calendrierExistant);

            Data savedData = dataService.saveData(data);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedData);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
