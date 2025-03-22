package com.example.epivizappapi.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Pandemie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String type;

    @OneToMany(mappedBy = "pandemie", cascade = CascadeType.ALL)
    private List<Data> data;

    // Constructeur par défaut
    public Pandemie() {}


    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
