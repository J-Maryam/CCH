package com.cch.services.Impl;

import com.cch.dtos.request.CyclistRequestDTO;
import com.cch.dtos.response.CyclistResponseDTO;
import com.cch.entities.Cyclist;
import com.cch.entities.Team;
import com.cch.mappers.CyclistMapper;
import com.cch.repositories.CyclistRepository;
import com.cch.repositories.TeamRepository;
import com.cch.services.CyclistService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Validated
public class CyclistServiceImpl implements CyclistService {

    private final CyclistRepository cyclistRepository;
    private final CyclistMapper cyclistMapper;
    private final TeamRepository teamRepository;

    public CyclistServiceImpl(CyclistRepository cyclistRepository, CyclistMapper cyclistMapper, TeamRepository teamRepository) {
        this.cyclistRepository = cyclistRepository;
        this.cyclistMapper = cyclistMapper;
        this.teamRepository = teamRepository;
    }

    @Override
    public CyclistResponseDTO save(CyclistRequestDTO cyclistRequestDTO) {
        validateCyclistRequest(cyclistRequestDTO);
        Cyclist cyclist = cyclistMapper.toEntity(cyclistRequestDTO);
        Cyclist savedCyclist = cyclistRepository.save(cyclist);
        return cyclistMapper.toResponseDto(savedCyclist);
    }

    @Override
    public Optional<CyclistResponseDTO> getById(Long id) {
        if (!cyclistRepository.existsById(id)) {
            throw new EntityNotFoundException("Cyclist with ID " + id + " not found");
        }
        return cyclistRepository.findById(id)
                .map(cyclistMapper::toResponseDto);
    }

    @Override
    public List<CyclistResponseDTO> getAll() {
        return cyclistRepository.findAll().stream()
                .map(cyclistMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CyclistResponseDTO update(Long id, CyclistRequestDTO cyclistRequestDTO) {
        Cyclist existingCyclist = cyclistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cyclist with ID " + id + " not found"));

        validateCyclistRequest(cyclistRequestDTO);

        Team team = teamRepository.findById(cyclistRequestDTO.teamId())
                .orElseThrow(() -> new EntityNotFoundException("Team with ID " + cyclistRequestDTO.teamId() + " not found"));

        existingCyclist.setFName(cyclistRequestDTO.fName());
        existingCyclist.setLName(cyclistRequestDTO.lName());
        existingCyclist.setBirthDate(cyclistRequestDTO.birthDate());
        existingCyclist.setNationality(cyclistRequestDTO.nationality());
        existingCyclist.setTeam(team);

        Cyclist updatedCyclist = cyclistRepository.save(existingCyclist);
        return cyclistMapper.toResponseDto(updatedCyclist);
    }

    @Override
    public void delete(Long id) {
        if (!cyclistRepository.existsById(id)) {
            throw new EntityNotFoundException("Cyclist with id " + id + " not found");
        }
        cyclistRepository.deleteById(id);
    }

    @Override
    public List<CyclistResponseDTO> findAllSortedByLastName(String lName) {
        return cyclistRepository.findAllSortedBylName(lName)
                .stream().map(cyclistMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CyclistResponseDTO> findAllSortedByNationality(String nationality) {
        return cyclistRepository.findAllSortedByNationality(nationality)
                .stream().map(cyclistMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CyclistResponseDTO> findAllSortedByTeam(Team team) {
        return cyclistRepository.findAllSortedByTeam(team).stream()
                .map(cyclistMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    private void validateCyclistRequest(CyclistRequestDTO cyclistRequestDTO) {
        if (cyclistRequestDTO.fName() == null || cyclistRequestDTO.fName().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        if (cyclistRequestDTO.lName() == null || cyclistRequestDTO.lName().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        if (cyclistRequestDTO.birthDate() == null || cyclistRequestDTO.birthDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birth date must be in the past");
        }
        if (cyclistRequestDTO.teamId() == null) {
            throw new IllegalArgumentException("Cyclist must belong to a team");
        }
    }
}