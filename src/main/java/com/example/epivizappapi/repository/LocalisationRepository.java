package com.example.epivizappapi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.epivizappapi.model.Localisation;

@Repository
public interface LocalisationRepository extends JpaRepository<Localisation, Long> {
}