package com.example.epivizappapi.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Localisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String country;

    @Column(length = 50, nullable = false)
    private String continent;

    @OneToMany(mappedBy = "localisation", cascade = CascadeType.ALL)
    private List<Data> data;

    // Constructeur par défaut
    public Localisation() {}

    // Constructeur avec paramètres
    public Localisation(String country, String continent) {
        this.country = country;
        this.continent = continent;
    }


    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
