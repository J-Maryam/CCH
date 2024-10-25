package com.cch.services.Impl;

import com.cch.dtos.request.TeamRequestDTO;
import com.cch.dtos.response.TeamResponseDTO;
import com.cch.entities.Team;
import com.cch.mappers.TeamMapper;
import com.cch.repositories.TeamRepository;
import com.cch.services.TeamService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    public TeamServiceImpl(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    @Override
    public TeamResponseDTO save(TeamRequestDTO teamRequestDTO) {
        Team team = teamMapper.toEntity(teamRequestDTO);
        Team savedTeam = teamRepository.save(team);
        return teamMapper.toResponseDto(savedTeam);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TeamResponseDTO> getById(Long id) {
        return teamRepository.findById(id)
                .map(teamMapper::toResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamResponseDTO> getAll() {
        return teamRepository.findAll().stream()
                .map(teamMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public TeamResponseDTO update(Long id, TeamRequestDTO teamRequestDTO) {
        Team existingTeam = teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team with ID " + id + " not found"));

        Team updatedTeam = teamMapper.toEntity(teamRequestDTO);
        updatedTeam.setId(existingTeam.getId());

        Team savedTeam = teamRepository.save(updatedTeam);
        return teamMapper.toResponseDto(savedTeam);
    }

    @Override
    public void delete(Long id) {
        if (!teamRepository.existsById(id)) {
            throw new EntityNotFoundException("Team with ID " + id + " not found");
        }
        teamRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public TeamResponseDTO findByTeamName(String teamName) {
        return teamMapper.toResponseDto(teamRepository.findByTeam(teamName));
    }
}
