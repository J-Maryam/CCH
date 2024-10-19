package com.cch.services;

import com.cch.entities.Cyclist;
import com.cch.entities.Team;
import com.cch.repositories.CyclistRepository;
import com.cch.services.Impl.CyclistServiceImpl;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CyclistServiceTest {

    @Mock
    private CyclistRepository cyclistRepository;

    @InjectMocks
    private CyclistServiceImpl cyclistService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCyclist_Success() {
        Team team = new Team("Team A");

        Cyclist cyclist = new Cyclist("Jean", "Russo", "USA", LocalDate.of(2000, 5, 19), team);

        when(cyclistRepository.save(cyclist)).thenReturn(cyclist);

        Cyclist savedCyclist = cyclistService.save(cyclist);

        assertNotNull(savedCyclist);
        assertEquals("Jean", savedCyclist.getFName());
        assertEquals("Russo", savedCyclist.getLName());
        assertEquals("USA", savedCyclist.getNationality());
        assertEquals(LocalDate.of(2000, 5, 19), savedCyclist.getBirthDate());
        assertEquals("Team A", savedCyclist.getTeam().getTeam());
        verify(cyclistRepository, times(1)).save(cyclist);
    }

    @Test
    public void testSaveCyclist_InvalidFirstName_ShouldThrowException() {
        Cyclist cyclist = new Cyclist("", "Russo", "USA", LocalDate.of(2000, 5, 19), new Team("Team A"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> cyclistService.save(cyclist));

        assertEquals("First name cannot be empty", exception.getMessage());
        verify(cyclistRepository, never()).save(any(Cyclist.class));
    }


    @Test
    public void testSaveCyclist_InvalidBirthDate_ShouldThrowException() {
        Cyclist cyclist = new Cyclist("Jean", "Russo", "USA", LocalDate.of(2025, 1, 1), new Team("Team A"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> cyclistService.save(cyclist));

        assertEquals("Birth date must be in the past", exception.getMessage());
        verify(cyclistRepository, never()).save(any(Cyclist.class));
    }

    @Test
    public void testSaveCyclist_NoTeam_ShouldThrowException() {
        Cyclist cyclist = new Cyclist("Jean", "Russo", "USA", LocalDate.of(2000, 5, 19), null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> cyclistService.save(cyclist));

        assertEquals("Cyclist must belong to a team", exception.getMessage());
        verify(cyclistRepository, never()).save(any(Cyclist.class));
    }

    @Test
    public void findAllCyclists_Success() {
        Team teamA = new Team("Team A");
        Cyclist cyclist1 = new Cyclist("Jean", "Russo", "USA", LocalDate.of(2000, 5, 19), teamA);
        Cyclist cyclist2 = new Cyclist("Anna", "Smith", "Canada", LocalDate.of(1995, 3, 15), teamA);

        when(cyclistRepository.findAll()).thenReturn(Arrays.asList(cyclist1, cyclist2));

        List<Cyclist> cyclists = cyclistService.findAll();
        assertNotNull(cyclists);
        assertEquals(2, cyclists.size());

        assertEquals("Jean", cyclists.get(0).getFName());
        assertEquals("Russo", cyclists.get(0).getLName());
        assertEquals("USA", cyclists.get(0).getNationality());
        assertEquals(LocalDate.of(2000, 5, 19), cyclists.get(0).getBirthDate());
        assertEquals("Team A", cyclists.get(0).getTeam().getTeam());

        assertEquals("Anna", cyclists.get(1).getFName());
        assertEquals("Smith", cyclists.get(1).getLName());
        assertEquals("Canada", cyclists.get(1).getNationality());
        assertEquals(LocalDate.of(1995, 3, 15), cyclists.get(1).getBirthDate());
        assertEquals("Team A", cyclists.get(1).getTeam().getTeam());

        verify(cyclistRepository, times(1)).findAll();
    }

    @Test
    public void testFindById_CycleExists() {
        Long cyclistId = 1L;
        Cyclist cyclist = new Cyclist("Jean", "Russo", "USA", LocalDate.of(2000, 5, 19), null);

        when(cyclistRepository.findById(cyclistId)).thenReturn(Optional.of(cyclist));

        Optional<Cyclist> foundCyclist = cyclistService.findById(cyclistId);
        assertTrue(foundCyclist.isPresent());
        assertEquals("Jean", foundCyclist.get().getFName());
        assertEquals("Russo", foundCyclist.get().getLName());
        assertEquals(cyclist.getNationality(), foundCyclist.get().getNationality(), "Nationality should match");
        assertEquals(cyclist.getBirthDate(), foundCyclist.get().getBirthDate(), "Birth date should match");
    }

    @Test
    public void testFindById_CyclistDoesNotExist() {
        Long cyclistId = 2L;
        when(cyclistRepository.findById(cyclistId)).thenReturn(Optional.empty());
        Optional<Cyclist> foundCyclist = cyclistService.findById(cyclistId);
        assertFalse(foundCyclist.isPresent(),  "Cyclist should not be found");
    }

    @Test
    public void testDeleteById_CyclistDoesNotExist() {
        Long cyclistId = 2L;
        cyclistService.deleteById(cyclistId);
        verify(cyclistRepository, times(1)).deleteById(cyclistId);
    }

    @Test
    void testDeleteById_CyclistExists() {
        Long cyclistId = 1L;
        Cyclist cyclist = new Cyclist("Jean", "Russo", "USA", LocalDate.of(2000, 5, 19), null);

        when(cyclistRepository.findById(cyclistId)).thenReturn(Optional.of(cyclist));
        cyclistService.deleteById(cyclistId);
        verify(cyclistRepository, times(1)).deleteById(cyclistId);
    }
}