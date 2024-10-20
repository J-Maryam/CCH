package com.cch.services.Impl;

import com.cch.entities.Competition;
import com.cch.entities.Stage;
import com.cch.entities.enums.StageType;
import com.cch.repositories.StageRepository;
import com.cch.services.StageService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StageServiceImplTest {

    @Mock
    private StageRepository stageRepository;

    @InjectMocks
    private StageServiceImpl stageService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveStage() {
        Competition competition = new Competition("Tour de France", "France", LocalDate.of(2024, 12, 1), LocalDate.of(2025, 2, 23));
        Stage stage = new Stage(1, "Paris", "Lyon", LocalDate.of(2024, 6, 1), LocalTime.of(9, 30), StageType.FLAT, competition);

        when(stageRepository.save(stage)).thenReturn(stage);

        Stage savedStage = stageService.saveStage(stage);

        assertNotNull(savedStage);
        assertEquals("Paris", savedStage.getStartLocation());
        verify(stageRepository, times(1)).save(stage);
    }

    @Test
    void testInvalidStageNumber() {
        Competition competition = new Competition("Tour de France", "France", LocalDate.of(2024, 12, 1), LocalDate.of(2025, 2, 23));
        Stage stage = new Stage(-1, "Paris", "Lyon", LocalDate.of(2024, 6, 1), LocalTime.of(9, 30), StageType.FLAT, competition);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            stageService.saveStage(stage);
        });

        assertEquals("Le numéro du stage doit être positif.", exception.getMessage());
        verify(stageRepository, never()).save(stage);
    }

    @Test
    void testSameStartAndEndLocation() {
        Competition competition = new Competition("Tour de France", "France", LocalDate.of(2024, 12, 1), LocalDate.of(2025, 2, 23));
        Stage stage = new Stage(1, "Paris", "Paris", LocalDate.of(2024, 6, 1), LocalTime.of(9, 30), StageType.FLAT, competition);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            stageService.saveStage(stage);
        });

        assertEquals("Les lieux de départ et d'arrivée doivent être différents.", exception.getMessage());
        verify(stageRepository, never()).save(stage);
    }

    @Test
    void testNoStartTime() {
        Competition competition = new Competition("Tour de France", "France", LocalDate.of(2024, 12, 1), LocalDate.of(2025, 2, 23));
        Stage stage = new Stage(1, "Paris", "Lyon", LocalDate.of(2024, 6, 1), null, StageType.FLAT, competition);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            stageService.saveStage(stage);
        });

        assertEquals("L'heure de départ doit être spécifiée.", exception.getMessage());
        verify(stageRepository, never()).save(stage);
    }

    @Test
    void testNoCompetition() {
        Stage stage = new Stage(1, "Paris", "Lyon", LocalDate.of(2024, 6, 1), LocalTime.of(9, 30), StageType.FLAT, null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            stageService.saveStage(stage);
        });

        assertEquals("La compétition ne doit pas être nulle.", exception.getMessage());
        verify(stageRepository, never()).save(stage);
    }

    @Test
    void testGetAllStagesWithStages() {
        Competition competition = new Competition("Tour de France", "France", LocalDate.of(2024, 12, 1), LocalDate.of(2025, 2, 23));
        Stage stage1 = new Stage(1, "Paris", "Lyon", LocalDate.of(2024, 6, 1), LocalTime.of(9, 30), StageType.FLAT, competition);
        Stage stage2 = new Stage(2, "Lyon", "Marseille", LocalDate.of(2024, 6, 2), LocalTime.of(10, 0), StageType.MOUNTAIN, competition);

        when(stageRepository.findAll()).thenReturn(Arrays.asList(stage1, stage2));

        List<Stage> stages = stageService.getStages();

        assertNotNull(stages);
        assertEquals(2, stages.size());
        assertEquals("Paris", stages.get(0).getStartLocation());
        assertEquals("Lyon", stages.get(1).getStartLocation());
        verify(stageRepository, times(1)).findAll();
    }

    @Test
    void testGetAllStagesEmpty() {
        when(stageRepository.findAll()).thenReturn(Collections.emptyList());

        List<Stage> stages = stageService.getStages();

        assertNotNull(stages);
        assertTrue(stages.isEmpty());
        verify(stageRepository, times(1)).findAll();
    }

    @Test
    void testGetStageByIdSuccess() {
        Competition competition = new Competition("Tour de France", "France", LocalDate.of(2024, 7, 1), LocalDate.of(2024, 7, 23));
        Stage stage = new Stage(1, "Paris", "Lyon", LocalDate.of(2024, 7, 10), LocalTime.of(9, 0), StageType.FLAT, competition);

        when(stageRepository.getReferenceById(1L)).thenReturn(stage);

        Stage retrievedStage = stageService.getStageById(1L);

        assertNotNull(retrievedStage);
        assertEquals(1, retrievedStage.getNumber());
        assertEquals("Paris", retrievedStage.getStartLocation());
        verify(stageRepository, times(1)).getReferenceById(1L);
    }

    @Test
    void testGetStageByIdNotFound() {
        when(stageRepository.getReferenceById(999L)).thenThrow(new EntityNotFoundException("Stage non trouvé avec ID 999"));

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            stageService.getStageById(999L);
        });

        assertEquals("Stage non trouvé avec ID 999", exception.getMessage());
        verify(stageRepository, times(1)).getReferenceById(999L);
    }

    @Test
    void testGetStageByIdWithNullId() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            stageService.getStageById(null);
        });

        assertEquals("ID ne peut pas être null", exception.getMessage());
        verify(stageRepository, times(0)).getReferenceById(any());
    }

    @Test
    void testGetStageByIdWithNegativeId() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            stageService.getStageById(-2L);
        });

        assertEquals("ID ne peut pas être négatif", exception.getMessage());
        verify(stageRepository, times(0)).getReferenceById(any());
    }


}