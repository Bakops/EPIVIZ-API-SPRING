package com.example.epivizappapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.epivizappapi.dto.DataSummaryDTO;
import com.example.epivizappapi.model.Data;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {

        @Query("SELECT d FROM Data d WHERE d.calendrier IS NOT NULL")
        List<Data> findAllWithValidCalendrier();

        List<Data> findByPandemieId(Long pandemieId);

        List<Data> findByPandemieIdAndLocalisationId(Long pandemieId, Long localisationId);

        @Query("SELECT new com.example.epivizappapi.dto.DataSummaryDTO(SUM(d.totalCases), SUM(d.totalDeaths), SUM(d.newCases), SUM(d.newDeaths)) "
                        +
                        "FROM Data d")
        DataSummaryDTO calculateTotals();
}