package com.cch.services.Impl;

import com.cch.dtos.request.StageRequestDTO;
import com.cch.dtos.response.StageResponseDTO;
import com.cch.entities.Competition;
import com.cch.entities.Stage;
import com.cch.mappers.StageMapper;
import com.cch.repositories.CompetitionRepository;
import com.cch.repositories.StageRepository;
import com.cch.services.StageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class StageServiceImpl implements StageService {

    private final StageRepository stageRepository;
    private final StageMapper stageMapper;
    private final CompetitionRepository competitionRepository;

    @Override
    public StageResponseDTO save(StageRequestDTO stageRequestDTO) {
        if (stageRequestDTO.number() < 0) {
            throw new IllegalArgumentException("Le numéro du stage doit être positif.");
        }
        if (stageRequestDTO.startLocation().equals(stageRequestDTO.endLocation())) {
            throw new IllegalArgumentException("Le lieu de départ et d'arrivée ne peuvent pas être identiques.");
        }
        if (stageRequestDTO.startTime() == null) {
            throw new IllegalArgumentException("L'heure de départ est obligatoire.");
        }
        if (stageRequestDTO.competitionId() == null) {
            throw new IllegalArgumentException("La compétition est obligatoire pour créer un stage.");
        }

        Competition competition = competitionRepository.findById(stageRequestDTO.competitionId())
                .orElseThrow(() -> new EntityNotFoundException("Competition avec Id " + stageRequestDTO.competitionId() + " non trouvé"));

        Stage stage = stageMapper.toEntity(stageRequestDTO);
        stage.setCompetition(competition);

        Stage savedStage = stageRepository.save(stage);
        return stageMapper.toResponseDto(savedStage);
    }

    @Override
    public Optional<StageResponseDTO> getById(Long id) {
        return stageRepository.findById(id)
                .map(stageMapper::toResponseDto);
    }

    @Override
    public List<StageResponseDTO> getAll() {
        return stageRepository.findAll()
                .stream()
                .map(stageMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public StageResponseDTO update(Long id, StageRequestDTO stageRequestDTO) {
        Stage existingStage = stageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stage with Id " + id + " not found"));

        existingStage.setNumber(stageRequestDTO.number());
        existingStage.setStartLocation(stageRequestDTO.startLocation());
        existingStage.setEndLocation(stageRequestDTO.endLocation());
        existingStage.setDate(stageRequestDTO.date());
        existingStage.setStartTime(stageRequestDTO.startTime());
        existingStage.setStageType(stageRequestDTO.stageType());

        if (stageRequestDTO.competitionId() != null) {
            Competition competition = competitionRepository.findById(stageRequestDTO.competitionId())
                    .orElseThrow(() -> new EntityNotFoundException("Competition with Id " + stageRequestDTO.competitionId() + " not found"));
            existingStage.setCompetition(competition);
        }

        Stage updatedStage = stageRepository.save(existingStage);

        return stageMapper.toResponseDto(updatedStage);
    }

    @Override
    public void delete(Long id) {
        if (!stageRepository.existsById(id)) {
            throw new EntityNotFoundException("Stage with Id " + id + " not found");
        }
        stageRepository.deleteById(id);
    }
}
