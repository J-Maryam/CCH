package com.cch.services.Impl;

import com.cch.entities.Team;
import com.cch.repositories.TeamRepository;
import com.cch.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team save(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team findByTeamName(String teamName) {
        return teamRepository.findByTeam(teamName);
    }
}
