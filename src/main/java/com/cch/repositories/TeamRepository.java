package com.cch.repositories;

import com.cch.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findByTeam(String teamName);

}
