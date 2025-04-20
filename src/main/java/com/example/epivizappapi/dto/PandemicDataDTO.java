

package com.example.epivizappapi.dto;

public class PandemicDataDTO {
    private String date;
    private String country;
    private int cases;
    private int deaths;
    private int recovered;

    public PandemicDataDTO(String date, String country, int cases, int deaths, int recovered) {
        this.date = date;
        this.country = country;
        this.cases = cases;
        this.deaths = deaths;
        this.recovered = recovered;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }
}