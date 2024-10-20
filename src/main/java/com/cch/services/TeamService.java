package com.cch.services;

import com.cch.entities.Team;

public interface TeamService {
    Team save(Team team);
    Team findByTeamName(String teamName);
}
