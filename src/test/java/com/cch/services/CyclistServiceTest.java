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

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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

//    @Test
//    public void testSaveCyclist_InvalidData_ShouldThrowException() {
//        Team team = new Team("Team A");
//
//        Cyclist cyclist = new Cyclist("Anna","","Canada",LocalDate.of(1995, 3, 15),team);
//
//        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> {
//            cyclistService.save(cyclist);
//        });
//
//        assertNotNull(exception.getMessage());
//        verify(cyclistRepository, never()).save(any(Cyclist.class));
//    }

//    @Test
//    public void testSaveCyclist_WithInvalidBirthDate_ShouldThrowValidationException() {
//
//        Team team = new Team("Team A");
//        Cyclist cyclist = new Cyclist("Jean", "Russo", "USA", LocalDate.of(2030, 5, 19), team);
//
//        assertThrows(ConstraintViolationException.class, () -> {
//            cyclistService.save(cyclist);
//        });
//    }

//    @Test
//    public void testSaveCyclist_InvalidFirstName() {
//        Team team = new Team("Team A");
//
//        Cyclist cyclist = new Cyclist("", "Russo", "USA", LocalDate.of(2000, 5, 19), team);
//
//        Cyclist savedCyclist = cyclistService.save(cyclist);
//
//        assertNull(savedCyclist);
//        verify(cyclistRepository, never()).save(any(Cyclist.class));
//    }

//    @Test
//    public void testSaveCyclist_InvalidBirthDate() {
//        Team team = new Team("Team A");
//
//        Cyclist cyclist = new Cyclist("Jean", "Russo", "USA", LocalDate.of(2030, 5, 19), team);
//
//        assertThrows(ConstraintViolationException.class, () -> cyclistService.save(cyclist));
//
//        verify(cyclistRepository, never()).save(any(Cyclist.class));
//    }

//    @Test
//    public void testSaveCyclist_NoTeam() {
//        Cyclist cyclist = new Cyclist("Jean", "Russo", "USA", LocalDate.of(2000, 5, 19), null);
//
//        assertThrows(ConstraintViolationException.class, () -> cyclistService.save(cyclist));
//
//        verify(cyclistRepository, never()).save(any(Cyclist.class));
//    }

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
}