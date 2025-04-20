package com.example.epivizappapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.epivizappapi.model.Data;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {

    @Query("SELECT d FROM Data d WHERE d.calendrier IS NOT NULL")
    List<Data> findAllWithValidCalendrier();
}
