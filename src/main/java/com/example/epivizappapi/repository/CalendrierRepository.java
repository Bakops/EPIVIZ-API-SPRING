package com.example.epivizappapi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.epivizappapi.model.Calendrier;

@Repository
public interface CalendrierRepository extends JpaRepository<Calendrier, Long> {
}