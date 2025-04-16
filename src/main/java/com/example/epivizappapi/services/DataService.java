package com.example.epivizappapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.epivizappapi.model.Data;
import com.example.epivizappapi.repository.CalendrierRepository;
import com.example.epivizappapi.repository.DataRepository;

@Service
public class DataService {

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private CalendrierRepository calendrierRepository;

    public Data saveData(Data data) {
        if (data.getCalendrier() == null || data.getCalendrier().getId() <= 0) {
            throw new IllegalArgumentException("Calendrier must have a valid ID.");
        }
        return dataRepository.save(data);
    }
}
