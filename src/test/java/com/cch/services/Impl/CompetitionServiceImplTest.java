package com.cch.services.Impl;

import com.cch.dtos.request.CompetitionRequestDTO;
import com.cch.dtos.response.CompetitionResponseDTO;
import com.cch.entities.Competition;
import com.cch.mappers.CompetitionMapper;
import com.cch.repositories.CompetitionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompetitionServiceImplTest {

    @Mock
    private CompetitionRepository competitionRepository;

    @Mock
    private CompetitionMapper competitionMapper;

    @InjectMocks
    private CompetitionServiceImpl competitionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCompetitionValidData() {
        CompetitionRequestDTO competitionRequestDTO = new CompetitionRequestDTO("Tour de France", LocalDate.of(2024, 12, 1), LocalDate.of(2025, 7, 23), "France");
        Competition competition = new Competition();
        competition.setName("Tour de France");
        competition.setLocation("France");
        competition.setStartDate(LocalDate.of(2024, 12, 1));
        competition.setEndDate(LocalDate.of(2025, 7, 23));

        CompetitionResponseDTO expectedResponse = new CompetitionResponseDTO(1L, "Tour de France", LocalDate.of(2024, 12, 1), LocalDate.of(2025, 7, 23), "France", null);

        when(competitionMapper.toEntity(competitionRequestDTO)).thenReturn(competition);
        when(competitionRepository.save(competition)).thenReturn(competition);
        when(competitionMapper.toResponseDto(competition)).thenReturn(expectedResponse);

        CompetitionResponseDTO savedCompetition = competitionService.save(competitionRequestDTO);

        assertNotNull(savedCompetition);
        assertEquals(expectedResponse, savedCompetition);

        verify(competitionRepository, times(1)).save(competition);
        verify(competitionMapper, times(1)).toEntity(competitionRequestDTO);
        verify(competitionMapper, times(1)).toResponseDto(competition);
    }

    @Test
    public void testUpdateCompetitionWithNonExistentId() {
        CompetitionRequestDTO competitionRequestDTO = new CompetitionRequestDTO("Giro d'Italia", LocalDate.of(2024, 5, 25), LocalDate.of(2024, 5, 30), "Italy");
        Long competitionId = 1L;

        when(competitionRepository.findById(competitionId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> competitionService.update(competitionId, competitionRequestDTO));

        assertEquals("Competition with ID " + competitionId + " not found", exception.getMessage());
        verify(competitionRepository, never()).save(any(Competition.class));
    }

    @Test
    public void testDeleteNonExistentCompetition() {
        Long nonExistentCompetitionId = 999L;

        when(competitionRepository.existsById(nonExistentCompetitionId)).thenReturn(false);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> competitionService.delete(nonExistentCompetitionId));

        assertEquals("Competition with ID " + nonExistentCompetitionId + " not found", exception.getMessage());
        verify(competitionRepository, never()).deleteById(nonExistentCompetitionId);
    }

}
