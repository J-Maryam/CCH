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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GeneralResultServiceImpl implements GeneralResultService {

    private final GeneralResultRepository generalResultRepository;
    private final GeneralResultMapper generalResultMapper;
    private final CyclistMapper cyclistMapper;
    private final CyclistRepository cyclistRepository;
    private final CompetitionRepository competitionRepository;

    @Autowired
    public GeneralResultServiceImpl(GeneralResultRepository generalResultRepository, GeneralResultMapper generalResultMapper, CyclistMapper cyclistMapper, CyclistRepository cyclistRepository, CompetitionRepository competitionRepository) {
        this.generalResultRepository = generalResultRepository;
        this.generalResultMapper = generalResultMapper;
        this.cyclistMapper = cyclistMapper;
        this.cyclistRepository = cyclistRepository;
        this.competitionRepository = competitionRepository;
    }

    @Override
    public GeneralResultResponseDTO inscrireCycliste(GeneralResultRequestDTO requestDTO) {
        Cyclist cyclist = cyclistRepository.findById(requestDTO.cyclistId())
                .orElseThrow(() -> new EntityNotFoundException("Cyclist with ID " + requestDTO.cyclistId() + " not found"));

        Competition competition = competitionRepository.findById(requestDTO.competitionId())
                .orElseThrow(() -> new EntityNotFoundException("Competition with ID " + requestDTO.competitionId() + " not found"));

        GeneralResult generalResult = generalResultMapper.toEntity(requestDTO);
        generalResult.setCyclist(cyclist);
        generalResult.setCompetition(competition);

        GeneralResult savedGeneralResult = generalResultRepository.save(generalResult);
        return generalResultMapper.toResponseDto(savedGeneralResult);
    }

    @Override
    public List<CyclistResponseDTO> consulterInscrits(Long competitionId) {
        return generalResultRepository.findAllByCompetitionId(competitionId)
                .stream()
                .map(generalResult -> cyclistMapper.toResponseDto(generalResult.getCyclist()))
                .collect(Collectors.toList());
    }

    @Override
    public void retirerCycliste(GeneralResultId generalResultId) {
        if(!generalResultRepository.existsById(generalResultId)){
            throw new EntityNotFoundException("Cyclist with ID " + generalResultId + " not found");
        }
        generalResultRepository.deleteById(generalResultId);
    }

    @Override
    public GeneralResultResponseDTO save(GeneralResultRequestDTO requestDTO) {
        GeneralResult generalResult = generalResultMapper.toEntity(requestDTO);
        GeneralResult savedGeneralResult = generalResultRepository.save(generalResult);
        return generalResultMapper.toResponseDto(savedGeneralResult);
    }

    @Override
    public Optional<GeneralResultResponseDTO> getById(GeneralResultId id) {
        return generalResultRepository.findById(id)
                .map(generalResultMapper::toResponseDto);
    }

    @Override
    public List<GeneralResultResponseDTO> getAll() {
        return generalResultRepository.findAll()
                .stream().map(generalResultMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public GeneralResultResponseDTO update(GeneralResultId generalResultId, GeneralResultRequestDTO generalResultRequestDTO) {
        return null;
    }

    @Override
    public void delete(GeneralResultId id) {
        if (!generalResultRepository.existsById(id)) {
            throw new EntityNotFoundException("GeneralResult with ID " + id + " not found");
        }
        generalResultRepository.deleteById(id);
    }
}
