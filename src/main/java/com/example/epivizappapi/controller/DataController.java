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

import com.example.epivizappapi.model.Data;
import com.example.epivizappapi.repository.DataRepository;

@RestController
@RequestMapping("/api/data")
public class DataController {

    @Autowired
    private DataRepository dataRepository;

    @GetMapping
    public List<Data> getAllData() {
        return dataRepository.findAll();
    }

    @PostMapping
    public Data createData(@RequestBody Data data) {
        return dataRepository.save(data);
    }

    @GetMapping("/{id}")
    public Data getDataById(@PathVariable Long id) {
        return dataRepository.findById(id).orElseThrow(() -> new RuntimeException("Donnée introuvable"));
    }

    @DeleteMapping("/{id}")
    public void deleteData(@PathVariable Long id) {
        dataRepository.deleteById(id);
    }
}