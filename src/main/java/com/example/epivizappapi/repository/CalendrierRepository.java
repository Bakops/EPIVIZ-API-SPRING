package com.example.epivizappapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.epivizappapi.model.Calendrier;

/**
 * Repository pour l'entité Calendrier.
 * Fournit des méthodes CRUD pour interagir avec la table "calendar" dans la
 * base de données.
 */
@Repository
public interface CalendrierRepository extends CrudRepository<Calendrier, Long> {
    // Ajoutez ici des méthodes personnalisées si nécessaire
}