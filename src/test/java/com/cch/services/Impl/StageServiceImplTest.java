package com.cch.services.Impl;

import com.cch.dtos.request.StageRequestDTO;
import com.cch.dtos.response.StageResponseDTO;
import com.cch.entities.Competition;
import com.cch.entities.Stage;
import com.cch.entities.enums.StageType;
import com.cch.mappers.StageMapper;
import com.cch.repositories.CompetitionRepository;
import com.cch.repositories.StageRepository;
import com.cch.services.Impl.StageServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StageServiceImplTest {

    @Mock
    private StageRepository stageRepository;

    @Mock
    private CompetitionRepository competitionRepository;

    @Mock
    private StageMapper stageMapper;

    @InjectMocks
    private StageServiceImpl stageService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInvalidStageNumber() {
        StageRequestDTO stageRequestDTO = new StageRequestDTO(-1, "Paris", "Lyon", LocalDate.of(2024, 6, 1), LocalTime.of(9, 30), StageType.FLAT, 1L);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            stageService.save(stageRequestDTO);
        });
        assertEquals("Le numéro du stage doit être positif.", exception.getMessage());
    }

    @Test
    void testSameStartAndEndLocation() {
        StageRequestDTO stageRequestDTO = new StageRequestDTO(1, "Paris", "Paris", LocalDate.of(2024, 6, 1), LocalTime.of(9, 30), StageType.FLAT, 1L);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            stageService.save(stageRequestDTO);
        });
        assertEquals("Le lieu de départ et d'arrivée ne peuvent pas être identiques.", exception.getMessage());
    }

    @Test
    void testNoStartTime() {
        StageRequestDTO stageRequestDTO = new StageRequestDTO(1, "Paris", "Lyon", LocalDate.of(2024, 6, 1), null, StageType.FLAT, 1L);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            stageService.save(stageRequestDTO);
        });
        assertEquals("L'heure de départ est obligatoire.", exception.getMessage());
    }

    @Test
    void testNoCompetition() {
        StageRequestDTO stageRequestDTO = new StageRequestDTO(1, "Paris", "Lyon", LocalDate.of(2024, 6, 1), LocalTime.of(9, 30), StageType.FLAT, null);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            stageService.save(stageRequestDTO);
        });
        assertEquals("La compétition est obligatoire pour créer un stage.", exception.getMessage());
    }

    @Test
    void testGetAllStagesEmpty() {
        when(stageRepository.findAll()).thenReturn(Collections.emptyList());
        List<StageResponseDTO> stages = stageService.getAll();
        assertNotNull(stages);
        assertTrue(stages.isEmpty());
    }

    @Test
    void testGetStageByIdSuccess() {
        Competition competition = new Competition("Tour de France", "France", LocalDate.of(2024, 7, 1), LocalDate.of(2024, 7, 23));
        Stage stage = new Stage(1, "Paris", "Lyon", LocalDate.of(2024, 7, 10), LocalTime.of(9, 0), StageType.FLAT, competition);
        StageResponseDTO stageResponseDTO = new StageResponseDTO(1L, 1, "Paris", "Lyon", LocalDate.of(2024, 7, 10), LocalTime.of(9, 0), StageType.FLAT, null, null);

        when(stageRepository.findById(1L)).thenReturn(Optional.of(stage));
        when(stageMapper.toResponseDto(stage)).thenReturn(stageResponseDTO);

        Optional<StageResponseDTO> retrievedStageOpt = stageService.getById(1L);

        assertTrue(retrievedStageOpt.isPresent());
        StageResponseDTO retrievedStage = retrievedStageOpt.get();
        assertEquals("Paris", retrievedStage.startLocation());
        verify(stageRepository, times(1)).findById(1L);
    }

    @Test
    void testGetStageByIdNotFound() {
        when(stageRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<StageResponseDTO> retrievedStageOpt = stageService.getById(999L);

        assertFalse(retrievedStageOpt.isPresent());
        verify(stageRepository, times(1)).findById(999L);
    }

    @Test
    void testDeleteStageNotFound() {
        Long stageId = 999L;
        when(stageRepository.existsById(stageId)).thenReturn(false);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            stageService.delete(stageId);
        });

        assertEquals("Stage with Id " + stageId + " not found", exception.getMessage());
        verify(stageRepository, times(0)).deleteById(stageId);
    }

    @Test
    void testGetAllStages() {
        Competition competition = new Competition("Tour de France", "France", LocalDate.of(2024, 7, 1), LocalDate.of(2024, 7, 23));
        Stage stage1 = new Stage(1, "Paris", "Lyon", LocalDate.of(2024, 7, 10), LocalTime.of(9, 0), StageType.FLAT, competition);
        Stage stage2 = new Stage(2, "Lyon", "Marseille", LocalDate.of(2024, 7, 11), LocalTime.of(8, 0), StageType.MOUNTAIN, competition);
        StageResponseDTO responseDTO1 = new StageResponseDTO(1L, 1, "Paris", "Lyon", LocalDate.of(2024, 7, 10), LocalTime.of(9, 0), StageType.FLAT, null, null);
        StageResponseDTO responseDTO2 = new StageResponseDTO(2L, 2, "Lyon", "Marseille", LocalDate.of(2024, 7, 11), LocalTime.of(8, 0), StageType.MOUNTAIN, null, null);

        when(stageRepository.findAll()).thenReturn(Arrays.asList(stage1, stage2));
        when(stageMapper.toResponseDto(stage1)).thenReturn(responseDTO1);
        when(stageMapper.toResponseDto(stage2)).thenReturn(responseDTO2);

        List<StageResponseDTO> stages = stageService.getAll();

        assertNotNull(stages);
        assertEquals(2, stages.size());
        verify(stageRepository, times(1)).findAll();
    }

}