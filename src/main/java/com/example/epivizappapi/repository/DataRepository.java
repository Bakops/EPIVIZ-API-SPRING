package com.example.epivizappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.epivizappapi.model.Data;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {
    
}