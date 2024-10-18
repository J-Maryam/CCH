package com.cch.services;

import com.cch.entities.Cyclist;

import java.util.List;
import java.util.Optional;

public interface CyclistService {
    Cyclist save(Cyclist cyclist);
    List<Cyclist> findAll();
    Optional<Cyclist> findById(Long id);
    void deleteById(Long id);
}
