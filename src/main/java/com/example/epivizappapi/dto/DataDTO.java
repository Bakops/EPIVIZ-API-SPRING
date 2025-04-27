package com.example.epivizappapi.dto;

import com.example.epivizappapi.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDate;

@NoArgsConstructor
public class DataDTO {
    private Long id;
    private Integer totalCases;
    private Integer totalDeaths;
    private Integer newCases;
    private Integer newDeaths;
    private Long idLocation;
    private Long idPandemic;
    private Long idCalendar;
    private LocalDate dateValue;


    public DataDTO(Long id, Integer totalCases, Integer totalDeaths, Integer newCases, Integer newDeaths,
                   Long idLocalisation, Long idPandemie, Long idCalendrier, LocalDate dateValue) {
        this.id = id;
        this.totalCases = totalCases;
        this.totalDeaths = totalDeaths;
        this.newCases = newCases;
        this.newDeaths = newDeaths;
        this.idLocation = idLocalisation;
        this.idPandemic = idPandemie;
        this.idCalendar = idCalendrier;
        this.dateValue = dateValue;

    }

    public Data toEntity(Localisation localisation, Pandemie pandemie, Calendrier calendrier) {
        Data data = new Data();
        data.setId(this.id);
        data.setTotalCases(this.totalCases);
        data.setTotalDeaths(this.totalDeaths);
        data.setNewCases(this.newCases);
        data.setNewDeaths(this.newDeaths);
        data.setLocalisation(localisation);
        data.setPandemie(pandemie);
        data.setCalendrier(calendrier);
        return data;
    }

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

    public Long getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Long idLocation) {
        this.idLocation = idLocation;
    }

    public Long getIdPandemic() {
        return idPandemic;
    }

    public void setIdPandemic(Long idPandemic) {
        this.idPandemic = idPandemic;
    }

    public Long getIdCalendar() {
        return idCalendar;
    }

    public void setIdCalendar(Long idCalendar) {
        this.idCalendar = idCalendar;
    }

    public LocalDate getDateValue() {
        return dateValue;
    }

    public void setDateValue(LocalDate dateValue) {
        this.dateValue = dateValue;
    }
}
