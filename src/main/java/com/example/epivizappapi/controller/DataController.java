package com.example.epivizappapi.controller;

import java.util.List;

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
    private final DataRepository repository;

    public DataController(DataRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Data> getAllData() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Data getDataById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @PostMapping
    public Data createData(@RequestBody Data data) {
        return repository.save(data);
    }


    @DeleteMapping("/{id}")
    public void deleteData(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
