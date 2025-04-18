package com.example.epivizappapi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "calendrier")
public class Calendrier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "calendrier")
    @JsonIgnoreProperties("calendrier")
    private List<Pandemie> pandemies = new ArrayList<>();

    public Calendrier() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Pandemie> getPandemies() {
        return pandemies;
    }

    public void setPandemies(List<Pandemie> pandemies) {
        this.pandemies = pandemies;
    }
}
