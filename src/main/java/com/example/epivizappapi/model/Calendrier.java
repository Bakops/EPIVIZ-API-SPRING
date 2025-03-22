package com.example.epivizappapi.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Calendrier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int date;

    @OneToMany(mappedBy = "calendrier", cascade = CascadeType.ALL)
    private List<Data> data;

    // Constructeur par défaut
    public Calendrier() {}


    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
