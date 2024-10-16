package com.cch.repositories;

import com.cch.entities.Competition;
import com.cch.entities.Cyclist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CyclistRepository extends JpaRepository<Competition, Integer> {
    Cyclist save(Cyclist cyclist);g
}
