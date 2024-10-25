package com.cch.services.Impl;

import com.cch.dtos.request.StageRequestDTO;
import com.cch.dtos.response.StageResponseDTO;
import com.cch.entities.Stage;
import com.cch.mappers.StageMapper;
import com.cch.repositories.CompetitionRepository;
import com.cch.repositories.StageRepository;
import com.cch.services.StageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class StageServiceImpl implements StageService {

    private final StageRepository stageRepository;
    private final StageMapper stageMapper;
    private final CompetitionRepository competitionRepository;

    @Autowired
    public StageServiceImpl(StageRepository stageRepository, StageMapper stageMapper, CompetitionRepository competitionRepository) {
        this.stageRepository = stageRepository;
        this.stageMapper = stageMapper;
        this.competitionRepository = competitionRepository;
    }

    @Override
    public StageResponseDTO save(StageRequestDTO stageRequestDTO) {
        Stage stage = stageMapper.toEntity(stageRequestDTO);
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
//        Stage existingStage = stageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Stage with Id " + id + "not found"));
//
//        existingStage.setNumber(stageRequestDTO.number());
//        existingStage.setStartLocation((stageRequestDTO.startLocation()));
//        existingStage.setEndLocation((stageRequestDTO.endLocation()));
//        existingStage.setDate(stageRequestDTO.date());
//        existingStage.setStartTime(stageRequestDTO.startTime());
//        existingStage.setStageType(stageRequestDTO.stageType());
//
//        if (stageRequestDTO.competitionId() != null) {
//            Competition competition = competitionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Competition with Id " + id + "not found"));
//            existingStage.setCompetition(competition);
//        }
        return null;
    }

    @Override
    public void delete(Long id) {
        if (!stageRepository.existsById(id)) {
            throw new EntityNotFoundException("Stage with Id" + id + "not found");
        }
        stageRepository.deleteById(id);
    }
}
