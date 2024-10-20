package com.cch.services.Impl;

import com.cch.entities.Competition;
import com.cch.repositories.CompetitionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CompetitionServiceImplTest {

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
        Competition competition = new Competition("Tour de France", "France", LocalDate.of(2024, 12, 1), LocalDate.of(2025, 7, 23));

        when(competitionRepository.save(any(Competition.class))).thenReturn(competition);

        Competition savedCompetition = competitionService.saveCompetition(competition);

        assertNotNull(savedCompetition);
        assertEquals("Tour de France", savedCompetition.getName());
        assertEquals("France", savedCompetition.getLocation());
        assertEquals(LocalDate.of(2024, 12, 1), savedCompetition.getStartDate());
        assertEquals(LocalDate.of(2025, 7, 23), savedCompetition.getEndDate());

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

    @Test
    public void testFindCompetitionById() {
        Competition competition = new Competition("Tour de France", "France", LocalDate.of(2024, 7, 1), LocalDate.of(2024, 7, 23));

        when(competitionRepository.findById(anyLong())).thenReturn(Optional.of(competition));

        Optional<Competition> foundCompetition = competitionService.findCompetitionById(1L);

        assertTrue(foundCompetition.isPresent());
        assertEquals("Tour de France", foundCompetition.get().getName());

        verify(competitionRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindCompetitionById_NotFound() {

        when(competitionRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Competition> foundCompetition = competitionService.findCompetitionById(2L);

        assertFalse(foundCompetition.isPresent());
        verify(competitionRepository, times(1)).findById(2L);
    }

    @Test
    public void testUpdateCompetitionValidData() {
        Competition existingCompetition = new Competition("Tour de France", "France", LocalDate.of(2030, 7, 1), LocalDate.of(2030, 7, 23));
        existingCompetition.setId(1L);

        when(competitionRepository.existsById(1L)).thenReturn(true);
        when(competitionRepository.save(any(Competition.class))).thenReturn(existingCompetition);

        Competition updatedCompetition = competitionService.updateCompetition(existingCompetition);

        assertNotNull(updatedCompetition);
        assertEquals("Tour de France", updatedCompetition.getName());
        assertEquals("France", updatedCompetition.getLocation());
        assertEquals(LocalDate.of(2030, 7, 1), updatedCompetition.getStartDate());
        assertEquals(LocalDate.of(2030, 7, 23), updatedCompetition.getEndDate());

        verify(competitionRepository, times(1)).save(existingCompetition);
    }

    @Test
    public void testUpdateCompetitionWithNonExistentId() {
        Competition competition = new Competition("Giro d'Italia", "Italy", LocalDate.of(2024, 5, 25), LocalDate.of(2024, 5, 30));
        competition.setId(1L);

        when(competitionRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            competitionService.updateCompetition(competition);
        });

        assertEquals("Competition does not exist", exception.getMessage());
        verify(competitionRepository, never()).save(competition);
    }

    @Test
    public void testUpdateCompetitionWithNullName() {
        Competition competition = new Competition(null, "Italy", LocalDate.of(2024, 5, 25), LocalDate.of(2024, 5, 30));
        competition.setId(1L);

        when(competitionRepository.existsById(1L)).thenReturn(true);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            competitionService.updateCompetition(competition);
        });

        assertEquals("Name cannot be null", exception.getMessage());
        verify(competitionRepository, never()).save(competition);
    }

    @Test
    public void testDeleteExistingCompetition() {
        Long competitionId = 1L;

        doNothing().when(competitionRepository).deleteById(competitionId);
        competitionService.deleteCompetition(competitionId);

        verify(competitionRepository, times(1)).deleteById(competitionId);
    }

    @Test
    public void testDeleteNonExistentCompetition() {
        Long nonExistentCompetitionId = 999L;

        doThrow(new EmptyResultDataAccessException(1)).when(competitionRepository).deleteById(nonExistentCompetitionId);

        Exception exception = assertThrows(EmptyResultDataAccessException.class, () -> {
            competitionService.deleteCompetition(nonExistentCompetitionId);
        });

        assertNotNull(exception);
        verify(competitionRepository, times(1)).deleteById(nonExistentCompetitionId);
    }

    @Test
    public void testDeleteCompetitionWithForeignKeyConstraint() {
        Long competitionId = 3L;

        doThrow(new DataIntegrityViolationException("Constraint violation")).when(competitionRepository).deleteById(competitionId);

        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            competitionService.deleteCompetition(competitionId);
        });

        assertEquals("Constraint violation", exception.getMessage());
        verify(competitionRepository, times(1)).deleteById(competitionId);
    }


}