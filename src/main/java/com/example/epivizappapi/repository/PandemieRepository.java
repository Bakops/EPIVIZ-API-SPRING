package com.example.epivizappapi.repository;
import com.example.epivizappapi.model.Pandemie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PandemieRepository extends JpaRepository<Pandemie, Long> {
}
