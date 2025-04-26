package com.example.epivizappapi.dto;

public class DataSummaryDTO {
    private Long totalCases;
    private Long totalDeaths;
    private Long newCases;
    private Long newDeaths;

    public DataSummaryDTO(long totalCases, long totalDeaths, long newCases, long newDeaths) {
        this.totalCases = totalCases;
        this.totalDeaths = totalDeaths;
        this.newCases = newCases;
        this.newDeaths = newDeaths;
    }

    public long getTotalCases() {
        return totalCases;
    }

    public long getTotalDeaths() {
        return totalDeaths;
    }

    public long getNewCases() {
        return newCases;
    }

    public long getNewDeaths() {
        return newDeaths;
    }
}