package com.cch.services.Impl;

import com.cch.entities.Competition;
import com.cch.entities.Stage;
import com.cch.entities.enums.StageType;
import com.cch.repositories.StageRepository;
import com.cch.services.StageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;

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
}