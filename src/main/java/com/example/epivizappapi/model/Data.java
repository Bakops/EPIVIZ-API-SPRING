package com.example.epivizappapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "data") 
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_cases", nullable = false)
    private int totalCases;

    @Column(name = "total_deaths", nullable = false)
    private int totalDeaths;

    @Column(name = "new_cases", nullable = false)
    private int newCases;

    @Column(name = "new_deaths", nullable = false)
    private int newDeaths;

    @ManyToOne
    @JoinColumn(name = "id_localisation", nullable = false)
    private Localisation localisation;

    @ManyToOne
    @JoinColumn(name = "id_pandemie", nullable = false)
    private Pandemie pandemie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendrier_id")
    private Calendrier calendrier;  

    public Data() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public int getNewCases() {
        return newCases;
    }

    public void setNewCases(int newCases) {
        this.newCases = newCases;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(int newDeaths) {
        this.newDeaths = newDeaths;
    }

    public Localisation getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }

    public Pandemie getPandemie() {
        return pandemie;
    }

    public void setPandemie(Pandemie pandemie) {
        this.pandemie = pandemie;
    }

    public Calendrier getCalendrier() {
        return calendrier;
    }

    public void setCalendrier(Calendrier calendrier) {
        this.calendrier = calendrier;
    }
}