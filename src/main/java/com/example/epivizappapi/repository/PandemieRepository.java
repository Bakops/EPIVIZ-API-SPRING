package com.example.epivizappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.epivizappapi.model.Pandemie;

@Repository
public interface PandemieRepository extends JpaRepository<Pandemie, Long> {
}