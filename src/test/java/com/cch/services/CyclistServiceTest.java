package com.cch.services;

import com.cch.entities.Cyclist;
import com.cch.entities.Team;
import com.cch.repositories.CyclistRepository;
import com.cch.services.Impl.CyclistServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    public void testSaveCyclist() {
        Team team = new Team("Team A");

        Cyclist cyclist = new Cyclist("Jean", "Russo", "USA", LocalDate.of(2000, 5, 19), team);

        when(cyclistRepository.save(cyclist)).thenReturn(cyclist);

        Cyclist savedCyclist = cyclistService.save(cyclist);

        assertNotNull(savedCyclist);
        assertEquals("Jean", savedCyclist.getFName());
        assertEquals("Russo", savedCyclist.getLName());
        assertEquals("USA", savedCyclist.getNationality());
        assertEquals(LocalDate.of(2000,5,19), savedCyclist.getBirthDate());
        assertEquals("Team A", savedCyclist.getTeam().getTeam());
        verify(cyclistRepository, times(1)).save(cyclist);
    }
}
