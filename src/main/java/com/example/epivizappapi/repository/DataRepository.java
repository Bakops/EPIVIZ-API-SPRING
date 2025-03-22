package com.example.epivizappapi.repository;

import com.example.epivizappapi.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {
    // Vous pouvez ajouter des méthodes de requête personnalisées ici si nécessaire
}
