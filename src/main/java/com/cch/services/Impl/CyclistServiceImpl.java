package com.cch.services.Impl;

import com.cch.dtos.request.CyclistRequestDTO;
import com.cch.dtos.response.CyclistResponseDTO;
import com.cch.entities.Cyclist;
import com.cch.entities.Team;
import com.cch.mappers.CyclistMapper;
import com.cch.repositories.CyclistRepository;
import com.cch.services.CyclistService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CyclistServiceImpl implements CyclistService {

    private final CyclistRepository cyclistRepository;
    private final CyclistMapper cyclistMapper;

    public CyclistServiceImpl(CyclistRepository cyclistRepository, CyclistMapper cyclistMapper) {
        this.cyclistRepository = cyclistRepository;
        this.cyclistMapper = cyclistMapper;
    }

    @Override
    public CyclistResponseDTO save(CyclistRequestDTO cyclistRequestDTO) {
        Cyclist cyclist = cyclistMapper.toEntity(cyclistRequestDTO);
        Cyclist savedCyclist = cyclistRepository.save(cyclist);
        return cyclistMapper.toResponseDto(savedCyclist);
    }

    @Override
    public Optional<CyclistResponseDTO> getById(Long id) {
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
                .orElseThrow(() -> new EntityNotFoundException("Cyclist with id " + id + " not found"));

        Cyclist updatedCyclist = cyclistMapper.toEntity(cyclistRequestDTO);
        updatedCyclist.setId(existingCyclist.getId());
        cyclistRepository.save(updatedCyclist);
        return cyclistMapper.toResponseDto(updatedCyclist);
    }

    @Override
    public void delete(Long id) {
        if(!cyclistRepository.existsById(id)) {
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
}