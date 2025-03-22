package com.example.epivizappapi.repository;
import com.example.epivizappapi.model.Localisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalisationRepository extends JpaRepository<Localisation, Long> {
}
