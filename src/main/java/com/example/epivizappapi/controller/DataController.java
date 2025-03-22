package com.example.epivizappapi.controller;

import com.example.epivizappapi.model.Data;
import com.example.epivizappapi.repository.DataRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

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
