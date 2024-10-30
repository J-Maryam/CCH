package com.cch.services.Impl;

import com.cch.dtos.request.GeneralResultRequestDTO;
import com.cch.dtos.response.CyclistResponseDTO;
import com.cch.dtos.response.GeneralResultResponseDTO;
import com.cch.entities.Competition;
import com.cch.entities.Cyclist;
import com.cch.entities.GeneralResult;
import com.cch.entities.embeddebals.GeneralResultId;
import com.cch.mappers.CyclistMapper;
import com.cch.mappers.GeneralResultMapper;
import com.cch.repositories.CompetitionRepository;
import com.cch.repositories.CyclistRepository;
import com.cch.repositories.GeneralResultRepository;
import com.cch.services.GeneralResultService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
public class GeneralResultServiceImpl implements GeneralResultService {

    private final GeneralResultRepository generalResultRepository;
    private final GeneralResultMapper generalResultMapper;
    private final CyclistRepository cyclistRepository;
    private final CompetitionRepository competitionRepository;

    @Override
    public GeneralResultResponseDTO inscrireCycliste(GeneralResultRequestDTO requestDTO) {
        Cyclist cyclist = cyclistRepository.findById(requestDTO.id().getCyclistId())
                .orElseThrow(() -> new EntityNotFoundException("Cyclist with ID " + requestDTO + " not found"));

        Competition competition = competitionRepository.findById(requestDTO.id().getCompetitionId())
                .orElseThrow(() -> new EntityNotFoundException("Competition with ID " + requestDTO + " not found"));

        GeneralResult generalResult = new GeneralResult(cyclist, competition);

        GeneralResult savedGeneralResult = generalResultRepository.save(generalResult);
        return generalResultMapper.toResponseDto(savedGeneralResult);
    }

    @Override
    public List<GeneralResultResponseDTO> consulterInscrits() {
        List<GeneralResult> generalResults = generalResultRepository.findAll();
        return generalResults.stream().map(generalResultMapper::toResponseDto).collect(Collectors.toList());
    }

    @Override
    public GeneralResultResponseDTO getGeneralResultById(GeneralResultId id) {
        Optional<GeneralResult> generalResult = generalResultRepository.findById(id);
        if (generalResult.isPresent()) {
            return generalResultMapper.toResponseDto(generalResult.get());
        } else {
            throw new EntityNotFoundException("General Result not found with id: " + id);
        }
    }

    @Override
    public GeneralResultResponseDTO updateGeneralResult(GeneralResultId id, GeneralResultRequestDTO requestDTO) {
        Optional<GeneralResult> existingResult = generalResultRepository.findById(id);
        if (existingResult.isPresent()) {
            GeneralResult generalResultToUpdate = generalResultMapper.toEntity(requestDTO);
            generalResultToUpdate.setId(id);
            GeneralResult updatedResult = generalResultRepository.save(generalResultToUpdate);
            return generalResultMapper.toResponseDto(updatedResult);
        } else {
            throw new EntityNotFoundException("General Result not found with id: " + id);
        }
    }

    @Override
    public void retirerCycliste(GeneralResultId id) {
        Optional<GeneralResult> generalResult = generalResultRepository.findById(id);
        if (generalResult.isPresent()) {
            generalResultRepository.delete(generalResult.get());
        } else {
            throw new EntityNotFoundException("General Result not found with id: " + id);
        }
    }

}
