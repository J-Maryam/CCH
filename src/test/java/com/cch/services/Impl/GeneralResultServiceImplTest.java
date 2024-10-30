package com.cch.services.Impl;

import com.cch.dtos.request.GeneralResultRequestDTO;
import com.cch.dtos.response.GeneralResultResponseDTO;
import com.cch.entities.Competition;
import com.cch.entities.Cyclist;
import com.cch.entities.GeneralResult;
import com.cch.entities.embeddebals.GeneralResultId;
import com.cch.mappers.GeneralResultMapper;
import com.cch.repositories.CompetitionRepository;
import com.cch.repositories.CyclistRepository;
import com.cch.repositories.GeneralResultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.EntityNotFoundException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GeneralResultServiceImplTest {

    @Mock
    private GeneralResultRepository generalResultRepository;

    @Mock
    private CyclistRepository cyclistRepository;

    @Mock
    private CompetitionRepository competitionRepository;

    @Mock
    private GeneralResultMapper generalResultMapper;

    @InjectMocks
    private GeneralResultServiceImpl service;

    private Competition competition;
    private Cyclist cyclist;
    private GeneralResult generalResult;
    private GeneralResultId generalResultId;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        competition = new Competition();
        competition.setId(1L);

        cyclist = new Cyclist();
        cyclist.setId(1L);
        cyclist.setFName("John");
        cyclist.setLName("Doe");
        cyclist.setNationality("USA");
        cyclist.setBirthDate(LocalDate.of(2000, 5, 19));

        generalResultId = new GeneralResultId(cyclist.getId(), competition.getId());

        generalResult = new GeneralResult(cyclist, competition);
    }

    @Test
    public void testInscrireCycliste() {
        GeneralResultRequestDTO requestDTO = new GeneralResultRequestDTO(generalResultId, Duration.ofHours(5), 1);
        GeneralResultResponseDTO responseDTO = new GeneralResultResponseDTO(null, null, Duration.ofHours(5), 1);

        when(cyclistRepository.findById(generalResultId.getCyclistId())).thenReturn(Optional.of(cyclist));
        when(competitionRepository.findById(generalResultId.getCompetitionId())).thenReturn(Optional.of(competition));
        when(generalResultRepository.save(any(GeneralResult.class))).thenReturn(generalResult);
        when(generalResultMapper.toResponseDto(any(GeneralResult.class))).thenReturn(responseDTO);

        GeneralResultResponseDTO result = service.inscrireCycliste(requestDTO);

        assertNotNull(result);
        assertEquals(1, result.generalRank());
        verify(generalResultRepository, times(1)).save(any(GeneralResult.class));
    }

    @Test
    public void testInscrireCyclisteCyclistNotFound() {
        GeneralResultRequestDTO requestDTO = new GeneralResultRequestDTO(generalResultId, Duration.ofHours(5), 1);

        when(cyclistRepository.findById(generalResultId.getCyclistId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.inscrireCycliste(requestDTO));
    }

    @Test
    public void testInscrireCyclisteCompetitionNotFound() {
        GeneralResultRequestDTO requestDTO = new GeneralResultRequestDTO(generalResultId, Duration.ofHours(5), 1);

        when(cyclistRepository.findById(generalResultId.getCyclistId())).thenReturn(Optional.of(cyclist));
        when(competitionRepository.findById(generalResultId.getCompetitionId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.inscrireCycliste(requestDTO));
    }

    @Test
    public void testConsulterInscrits() {
        GeneralResultResponseDTO responseDTO = new GeneralResultResponseDTO(null, null, Duration.ofHours(5), 1);

        when(generalResultRepository.findAll()).thenReturn(Collections.singletonList(generalResult));
        when(generalResultMapper.toResponseDto(any(GeneralResult.class))).thenReturn(responseDTO);

        List<GeneralResultResponseDTO> inscrits = service.consulterInscrits();

        assertEquals(1, inscrits.size());
        verify(generalResultRepository, times(1)).findAll();
    }
}
