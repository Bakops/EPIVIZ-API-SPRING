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
    private Integer totalCases;

    @Column(name = "total_deaths", nullable = false)
    private Integer totalDeaths;

    @Column(name = "new_cases", nullable = false)
    private Integer newCases;

    @Column(name = "new_deaths", nullable = false)
    private Integer newDeaths;

    @ManyToOne
    @JoinColumn(name = "id_location", nullable = false)
    private Localisation localisation;

    @ManyToOne
    @JoinColumn(name = "id_pandemie", nullable = false)
    private Pandemie pandemie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_calendar", nullable = false)
    private Calendrier calendrier;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(Integer totalCases) {
        this.totalCases = totalCases;
    }

    public Integer getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(Integer totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public Integer getNewCases() {
        return newCases;
    }

    public void setNewCases(Integer newCases) {
        this.newCases = newCases;
    }

    public Integer getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(Integer newDeaths) {
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
