package com.cch.services.Impl;

import com.cch.dtos.request.CompetitionRequestDTO;
import com.cch.dtos.response.CompetitionResponseDTO;
import com.cch.entities.Competition;
import com.cch.mappers.CompetitionMapper;
import com.cch.repositories.CompetitionRepository;
import com.cch.services.CompetitionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Validated
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final CompetitionMapper competitionMapper;

    @Override
    public CompetitionResponseDTO save(CompetitionRequestDTO competitionRequestDTO) {
        Competition competition = competitionMapper.toEntity(competitionRequestDTO);
        Competition savedCompetition = competitionRepository.save(competition);
        return competitionMapper.toResponseDto(savedCompetition);
    }

    @Override
    public Optional<CompetitionResponseDTO> getById(Long id) {
        if (!competitionRepository.existsById(id)) {
            throw new EntityNotFoundException("Competition with ID " + id + " not found");
        }
        return competitionRepository.findById(id)
                .map(competitionMapper::toResponseDto);
    }

    @Override
    public List<CompetitionResponseDTO> getAll() {
        return competitionRepository.findAll()
                .stream()
                .map(competitionMapper::toResponseDto)
                .toList();
    }

    @Override
    public CompetitionResponseDTO update(Long id, CompetitionRequestDTO competitionRequestDTO) {
        Competition existingCompetition = competitionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Competition with ID " + id + " not found"));

        existingCompetition.setName(competitionRequestDTO.name());
        existingCompetition.setStartDate(competitionRequestDTO.startDate());
        existingCompetition.setEndDate(competitionRequestDTO.endDate());
        existingCompetition.setLocation(competitionRequestDTO.location());
        Competition updatedCompetition = competitionRepository.save(existingCompetition);

        return competitionMapper.toResponseDto(updatedCompetition);
    }

    @Override
    public void delete(Long id) {
        if (!competitionRepository.existsById(id)) {
            throw new EntityNotFoundException("Competition with ID " + id + " not found");
        }
        competitionRepository.deleteById(id);
    }
}