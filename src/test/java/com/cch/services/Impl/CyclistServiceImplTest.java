package com.cch.services.Impl;

import com.cch.dtos.request.CyclistRequestDTO;
import com.cch.dtos.response.CyclistResponseDTO;
import com.cch.dtos.response.TeamDTO;
import com.cch.entities.Cyclist;
import com.cch.entities.Team;
import com.cch.mappers.CyclistMapper;
import com.cch.repositories.CyclistRepository;
import com.cch.repositories.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CyclistServiceImplTest {

    @Mock
    private CyclistRepository cyclistRepository;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private CyclistMapper cyclistMapper;

    @InjectMocks
    private CyclistServiceImpl cyclistService;

    private Team team;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        team = new Team("Team A");
    }

    @Test
    public void testSaveCyclist_Success() {
        CyclistRequestDTO requestDTO = new CyclistRequestDTO("Jean", "Russo", "USA", LocalDate.of(2000, 5, 19), 1L);
        Cyclist cyclist = new Cyclist("Jean", "Russo", "USA", LocalDate.of(2000, 5, 19), team);

        when(cyclistMapper.toEntity(requestDTO)).thenReturn(cyclist);
        when(cyclistRepository.save(any(Cyclist.class))).thenReturn(cyclist);
        when(cyclistMapper.toResponseDto(cyclist)).thenReturn(new CyclistResponseDTO(1L, "Jean", "Russo", "USA", LocalDate.of(2000, 5, 19), new TeamDTO(1L, "Team A")));

        CyclistResponseDTO responseDTO = cyclistService.save(requestDTO);

        assertNotNull(responseDTO);
        assertEquals("Jean", responseDTO.fName());
        assertEquals("Russo", responseDTO.lName());
        assertEquals("USA", responseDTO.nationality());
        assertEquals(LocalDate.of(2000, 5, 19), responseDTO.birthDate());
        assertEquals("Team A", responseDTO.team().team());

        verify(cyclistMapper, times(1)).toEntity(requestDTO);
        verify(cyclistRepository, times(1)).save(any(Cyclist.class));
        verify(cyclistMapper, times(1)).toResponseDto(cyclist);
    }

    @Test
    public void testSaveCyclist_InvalidFirstName_ShouldThrowException() {
        CyclistRequestDTO requestDTO = new CyclistRequestDTO("", "Russo", "USA", LocalDate.of(2000, 5, 19), 1L);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> cyclistService.save(requestDTO));

        assertEquals("First name cannot be empty", exception.getMessage());
        verify(cyclistRepository, never()).save(any(Cyclist.class));
    }

    @Test
    public void testSaveCyclist_InvalidBirthDate_ShouldThrowException() {
        CyclistRequestDTO requestDTO = new CyclistRequestDTO("Jean", "Russo", "USA", LocalDate.of(2025, 1, 1), 1L);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> cyclistService.save(requestDTO));

        assertEquals("Birth date must be in the past", exception.getMessage());
        verify(cyclistRepository, never()).save(any(Cyclist.class));
    }

    @Test
    public void testSaveCyclist_NoTeam_ShouldThrowException() {
        CyclistRequestDTO requestDTO = new CyclistRequestDTO("Jean", "Russo", "USA", LocalDate.of(2000, 5, 19), null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> cyclistService.save(requestDTO));

        assertEquals("Cyclist must belong to a team", exception.getMessage());
        verify(cyclistRepository, never()).save(any(Cyclist.class));
    }

    @Test
    public void testGetAllCyclists_Success() {
        Cyclist cyclist1 = new Cyclist("Jean", "Russo", "USA", LocalDate.of(2000, 5, 19), team);
        Cyclist cyclist2 = new Cyclist("Anna", "Smith", "Canada", LocalDate.of(1995, 3, 15), team);

        when(cyclistRepository.findAll()).thenReturn(Arrays.asList(cyclist1, cyclist2));
        when(cyclistMapper.toResponseDto(cyclist1)).thenReturn(new CyclistResponseDTO(1L, "Jean", "Russo", "USA", LocalDate.of(2000, 5, 19), new TeamDTO(1L, "Team A")));
        when(cyclistMapper.toResponseDto(cyclist2)).thenReturn(new CyclistResponseDTO(2L, "Anna", "Smith", "Canada", LocalDate.of(1995, 3, 15), new TeamDTO(1L, "Team A")));

        List<CyclistResponseDTO> cyclists = cyclistService.getAll();

        assertNotNull(cyclists);
        assertEquals(2, cyclists.size());
        assertEquals("Jean", cyclists.get(0).fName());
        assertEquals("Russo", cyclists.get(0).lName());
        assertEquals("USA", cyclists.get(0).nationality());
        assertEquals(LocalDate.of(2000, 5, 19), cyclists.get(0).birthDate());
        assertEquals("Team A", cyclists.get(0).team().team());

        assertEquals("Anna", cyclists.get(1).fName());
        assertEquals("Smith", cyclists.get(1).lName());
        assertEquals("Canada", cyclists.get(1).nationality());
        assertEquals(LocalDate.of(1995, 3, 15), cyclists.get(1).birthDate());
        assertEquals("Team A", cyclists.get(1).team().team());

        verify(cyclistRepository, times(1)).findAll();
    }

    @Test
    public void testGetById_CycleExists() {
        Long cyclistId = 1L;
        Cyclist cyclist = new Cyclist("Jean", "Russo", "USA", LocalDate.of(2000, 5, 19), team);

        when(cyclistRepository.existsById(cyclistId)).thenReturn(true);
        when(cyclistRepository.findById(cyclistId)).thenReturn(Optional.of(cyclist));
        when(cyclistMapper.toResponseDto(cyclist)).thenReturn(new CyclistResponseDTO(1L, "Jean", "Russo", "USA", LocalDate.of(2000, 5, 19), new TeamDTO(1L, "Team A")));

        Optional<CyclistResponseDTO> foundCyclist = cyclistService.getById(cyclistId);

        assertTrue(foundCyclist.isPresent());
        assertEquals("Jean", foundCyclist.get().fName());
        assertEquals("Russo", foundCyclist.get().lName());
    }


    @Test
    public void testGetById_CyclistDoesNotExist() {
        Long cyclistId = 2L;

        when(cyclistRepository.existsById(cyclistId)).thenReturn(false);
        when(cyclistRepository.findById(cyclistId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> cyclistService.getById(cyclistId));
    }

    @Test
    public void testUpdate_CyclistExists() {
        Long cyclistId = 1L;
        Long teamId = 1L;

        CyclistRequestDTO requestDTO = new CyclistRequestDTO("Jean", "Russo", "USA", LocalDate.of(2000, 5, 19), teamId);
        Cyclist existingCyclist = new Cyclist("Jean", "Russo", "USA", LocalDate.of(2000, 5, 19), team);

        when(cyclistRepository.findById(cyclistId)).thenReturn(Optional.of(existingCyclist));
        when(teamRepository.findById(teamId)).thenReturn(Optional.of(team));
        when(cyclistRepository.save(existingCyclist)).thenReturn(existingCyclist);
        when(cyclistMapper.toResponseDto(existingCyclist)).thenReturn(
                new CyclistResponseDTO(1L, "Jean", "Russo", "USA", LocalDate.of(2000, 5, 19), new TeamDTO(teamId, "Team A")));

        CyclistResponseDTO updatedCyclist = cyclistService.update(cyclistId, requestDTO);

        assertNotNull(updatedCyclist);
        assertEquals("Jean", updatedCyclist.fName());
        assertEquals("Russo", updatedCyclist.lName());
        verify(cyclistRepository, times(1)).findById(cyclistId);
        verify(teamRepository, times(1)).findById(teamId);
        verify(cyclistRepository, times(1)).save(existingCyclist);
    }

    @Test
    public void testUpdate_CyclistDoesNotExist() {
        Long cyclistId = 1L;
        CyclistRequestDTO requestDTO = new CyclistRequestDTO("Jean", "Russo", "USA", LocalDate.of(2000, 5, 19), 1L);
        when(cyclistRepository.findById(cyclistId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> cyclistService.update(cyclistId, requestDTO));

        assertEquals("Cyclist with ID 1 not found", exception.getMessage());
        verify(cyclistRepository, times(1)).findById(cyclistId);
        verify(cyclistRepository, never()).save(any(Cyclist.class));
    }

    @Test
    public void testDelete_CyclistExists() {
        Long cyclistId = 1L;
        when(cyclistRepository.existsById(cyclistId)).thenReturn(true);

        cyclistService.delete(cyclistId);

        verify(cyclistRepository, times(1)).deleteById(cyclistId);
    }

    @Test
    public void testDelete_CyclistDoesNotExist() {
        Long cyclistId = 2L;
        when(cyclistRepository.existsById(cyclistId)).thenReturn(false);

        Exception exception = assertThrows(EntityNotFoundException.class, () -> cyclistService.delete(cyclistId));

        assertEquals("Cyclist with id 2 not found", exception.getMessage());
        verify(cyclistRepository, never()).deleteById(cyclistId);
    }

}