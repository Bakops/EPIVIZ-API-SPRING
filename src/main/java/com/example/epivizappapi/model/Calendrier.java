package com.example.epivizappapi.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "calendar")
public class Calendrier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_value", nullable = false)
    private LocalDate dateValue;

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

<<<<<<< HEAD
    private LocalDate date; // Assuming the date field exists and is of type LocalDate

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
=======
    public LocalDate getDateValue() {
        return dateValue;
    }

    public void setDateValue(LocalDate dateValue) {
        this.dateValue = dateValue;
>>>>>>> a582667b8d01cab633de62404342084c6e77a940
    }
}