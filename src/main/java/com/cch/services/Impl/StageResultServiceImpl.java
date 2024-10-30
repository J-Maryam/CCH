package com.cch.services.Impl;

import com.cch.dtos.request.StageResultRequestDTO;
import com.cch.dtos.response.StageResultResponseDTO;
import com.cch.entities.Competition;
import com.cch.entities.Cyclist;
import com.cch.entities.Stage;
import com.cch.entities.StageResult;
import com.cch.entities.embeddebals.StageResultId;
import com.cch.mappers.StageResultMapper;
import com.cch.repositories.CompetitionRepository;
import com.cch.repositories.CyclistRepository;
import com.cch.repositories.StageRepository;
import com.cch.repositories.StageResultRepository;
import com.cch.services.StageResultService;
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
@Validated
@RequiredArgsConstructor
public class StageResultServiceImpl implements StageResultService {

    private final StageResultRepository stageResultRepository;
    private final StageResultMapper stageResultMapper;
    private final CyclistRepository cyclistRepository;
    private final StageRepository stageRepository;
//    private final CompetitionRepository competitionRepository;

    @Override
    public StageResultResponseDTO save(StageResultRequestDTO requestDTO) {

        Cyclist cyclist = cyclistRepository.findById(requestDTO.id().getCyclistId())
                .orElseThrow(() -> new EntityNotFoundException("Cyclist with ID " + requestDTO + " not found"));

        Stage stage = stageRepository.findById(requestDTO.id().getStageId())
                .orElseThrow(() -> new EntityNotFoundException("Stage with ID " + requestDTO + " not found"));

//        Optional<Competition> competitionOptional = competitionRepository.findByCyclistId(cyclist.getId());
//        if (competitionOptional.isEmpty()) {
//            throw new IllegalArgumentException("No competition found for cyclist with ID " + cyclist.getId());
//        }

        StageResult stageResult = new StageResult(cyclist, stage, requestDTO.time());

        StageResult savedStageResult = stageResultRepository.save(stageResult);
        return stageResultMapper.toResponseDto(savedStageResult);
    }

    @Override
    public Optional<StageResultResponseDTO> getById(StageResultId id) {
        StageResult stageResult = stageResultRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stage Result with ID " + id + " not found"));
        return Optional.of(stageResultMapper.toResponseDto(stageResult));
    }


    @Override
    public List<StageResultResponseDTO> getAll() {
        return stageResultRepository.findAll().stream()
                .map(stageResultMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public StageResultResponseDTO update(StageResultId id, StageResultRequestDTO requestDTO) {
        StageResult existingResult = stageResultRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stage Result with ID " + id + " not found"));

        existingResult.setTime(requestDTO.time());
        existingResult.setRank(requestDTO.rank());

        StageResult updatedResult = stageResultRepository.save(existingResult);
        return stageResultMapper.toResponseDto(updatedResult);
    }

    @Override
    public void delete(StageResultId id) {
        StageResult stageResult = stageResultRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stage Result with ID " + id + " not found"));
        stageResultRepository.delete(stageResult);
    }
}
