package com.example.epivizappapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.epivizappapi.model.Calendrier;

@Repository
public interface CalendrierRepository extends CrudRepository<Calendrier, Long> {
}
