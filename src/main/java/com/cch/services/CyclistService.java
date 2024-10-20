package com.cch.services;

import com.cch.entities.Cyclist;
import com.cch.entities.Team;

import java.util.List;
import java.util.Optional;

public interface CyclistService {
    Cyclist save(Cyclist cyclist);
    List<Cyclist> findAll();
    Optional<Cyclist> findById(Long id);
    void deleteById(Long id);
    List<Cyclist> findAllSortedByLastName(String lName);
    List<Cyclist> findAllSortedByNationality(String nationality);
    List<Cyclist> findAllSortedByTeam(Team team);
}
