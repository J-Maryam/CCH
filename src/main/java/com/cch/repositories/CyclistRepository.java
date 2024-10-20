package com.cch.repositories;

import com.cch.entities.Cyclist;
import com.cch.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CyclistRepository extends JpaRepository<Cyclist, Long> {

    List<Cyclist> findAllSortedBylName(String lName);
    List<Cyclist> findAllSortedByNationality(String nationality);
    List<Cyclist> findAllSortedByTeam(Team team);

}