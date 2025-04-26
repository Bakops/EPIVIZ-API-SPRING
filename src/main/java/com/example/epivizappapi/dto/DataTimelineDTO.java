package com.example.epivizappapi.dto;

import java.time.LocalDate;

/**
 * DTO (Data Transfer Object) pour représenter les données de la timeline.
 * Contient les informations sur les cas totaux, les décès, les nouveaux cas,
 * les nouveaux décès, et les dates associées.
 */
public class DataTimelineDTO {
    private Long totalCases; // Nombre total de cas
    private Long totalDeaths; // Nombre total de décès
    private Long newCases; // Nombre de nouveaux cas
    private Long newDeaths; // Nombre de nouveaux décès
    private LocalDate date; // Date brute
    private String formattedDate; // Date formatée pour l'affichage

    // Constructeur sans argument
    public DataTimelineDTO() {
    }

    // Constructeur avec tous les arguments
    public DataTimelineDTO(Long totalCases, Long totalDeaths, Long newCases, Long newDeaths, LocalDate date,
            String formattedDate) {
        this.totalCases = totalCases;
        this.totalDeaths = totalDeaths;
        this.newCases = newCases;
        this.newDeaths = newDeaths;
        this.date = date;
        this.formattedDate = formattedDate;
    }

    // Getters et Setters
    public Long getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(Long totalCases) {
        this.totalCases = totalCases;
    }

    public Long getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(Long totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public Long getNewCases() {
        return newCases;
    }

    public void setNewCases(Long newCases) {
        this.newCases = newCases;
    }

    public Long getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(Long newDeaths) {
        this.newDeaths = newDeaths;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }
}