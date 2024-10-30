package com.cch.services.Impl;

import com.cch.dtos.request.TeamRequestDTO;
import com.cch.dtos.response.TeamResponseDTO;
import com.cch.entities.Team;
import com.cch.mappers.TeamMapper;
import com.cch.repositories.TeamRepository;
import com.cch.services.TeamService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
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
        if (teamRepository.existsByTeam(teamRequestDTO.team())) {
            throw new IllegalArgumentException("Le nom de l'équipe doit être unique !");
        }

        Team team = teamMapper.toEntity(teamRequestDTO);
        Team savedTeam = teamRepository.save(team);
        return teamMapper.toResponseDto(savedTeam);
    }

    @Override
    public Optional<TeamResponseDTO> getById(Long id) {
        if (!teamRepository.existsById(id)) {
            throw new EntityNotFoundException("Team with ID " + id + " not found");
        }
        return teamRepository.findById(id)
                .map(teamMapper::toResponseDto);
    }

    @Override
    public List<TeamResponseDTO> getAll() {
        return teamRepository.findAll().stream()
                .map(teamMapper::toResponseDto)
                .toList();
    }

    @Override
    public TeamResponseDTO update(Long id, TeamRequestDTO teamRequestDTO) {
        Team existingTeam = teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team with ID " + id + " not found"));

        if (teamRepository.existsByTeam(teamRequestDTO.team())) {
            throw new IllegalArgumentException("Le nom de l'équipe doit être unique !");
        }

        existingTeam.setTeam(teamRequestDTO.team());
        Team updatedTeam = teamRepository.save(existingTeam);
        return teamMapper.toResponseDto(updatedTeam);
    }

    @Override
    public void delete(Long id) {
        if (!teamRepository.existsById(id)) {
            throw new EntityNotFoundException("Team with ID " + id + " not found");
        }
        teamRepository.deleteById(id);
    }

}
