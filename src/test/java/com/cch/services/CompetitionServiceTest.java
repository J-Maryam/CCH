package com.cch.services;

import com.cch.entities.Competition;
import com.cch.repositories.CompetitionRepository;
import com.cch.repositories.CyclistRepository;
import com.cch.services.Impl.CompetitionServiceImpl;
import com.cch.services.Impl.CyclistServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CompetitionServiceTest {

    @Mock
    private CompetitionRepository competitionRepository;

    @InjectMocks
    private CompetitionServiceImpl competitionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCompetitionValidData() {
        Competition competition = new Competition("Tour de France", "France", LocalDate.of(2024, 7, 1), LocalDate.of(2024, 7, 23));

        when(competitionRepository.save(any(Competition.class))).thenReturn(competition);

        Competition savedCompetition = competitionService.saveCompetition(competition);

        assertNotNull(savedCompetition);
        assertEquals("Tour de France", savedCompetition.getName());
        assertEquals("France", savedCompetition.getLocation());
        assertEquals(LocalDate.of(2024, 7, 1), savedCompetition.getStartDate());
        assertEquals(LocalDate.of(2024, 7, 23), savedCompetition.getEndDate());

        verify(competitionRepository, times(1)).save(competition);
    }

    @Test
    public void testCreateCompetitionWithNullValues() {
        Competition competition = new Competition(null, "France", LocalDate.of(2024, 7, 1), LocalDate.of(2024, 7, 23));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            competitionService.saveCompetition(competition);
        });

        assertEquals("Name cannot be null", exception.getMessage());

        verify(competitionRepository, never()).save(competition);
    }

    @Test
    public void testCreateCompetitionEndDateBeforeStartDate() {
        Competition competition = new Competition("Giro d'Italia", "Italy", LocalDate.of(2024, 5, 25), LocalDate.of(2024, 5, 1));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            competitionService.saveCompetition(competition);
        });

        String expectedMessage = "End date cannot be before start date";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testCreateCompetitionWithFutureDates() {
        Competition competition = new Competition("Future Race", "Australia", LocalDate.of(2030, 5, 25), LocalDate.of(2030, 5, 26));

        when(competitionRepository.save(any(Competition.class))).thenReturn(competition);

        Competition savedCompetition = competitionService.saveCompetition(competition);

        assertNotNull(savedCompetition);
        assertTrue(savedCompetition.getStartDate().isAfter(LocalDate.now()));
        assertTrue(savedCompetition.getEndDate().isAfter(savedCompetition.getStartDate()));

        verify(competitionRepository, times(1)).save(competition);
    }



}