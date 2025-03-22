package com.example.epivizappapi.model;

import jakarta.persistence.*;

@Entity
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int total_cases;
    private int total_deaths;
    private int new_cases;
    private int new_deaths;

    @ManyToOne
    @JoinColumn(name = "id_localisation")
    private Localisation localisation;

    @ManyToOne
    @JoinColumn(name = "id_pandemie")
    private Pandemie pandemie;

    @ManyToOne
    @JoinColumn(name = "id_calendrier")
    private Calendrier calendrier;

    // Constructeur par défaut
    public Data() {}

    // Constructeur avec paramètres
    public Data(int total_cases, int total_deaths, int new_cases, int new_deaths,
                Localisation localisation, Pandemie pandemie, Calendrier calendrier) {
        this.total_cases = total_cases;
        this.total_deaths = total_deaths;
        this.new_cases = new_cases;
        this.new_deaths = new_deaths;
        this.localisation = localisation;
        this.pandemie = pandemie;
        this.calendrier = calendrier;
    }
}
