package com.cch.services.Impl;

import com.cch.entities.Competition;
import com.cch.entities.Cyclist;
import com.cch.entities.GeneralResult;
import com.cch.entities.Team;
import com.cch.entities.embeddebals.GeneralResultId;
import com.cch.repositories.GeneralResultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GeneralResultServiceImplTest {

    @Mock
    private GeneralResultRepository repo;

    @InjectMocks
    private GeneralResultServiceImpl service;

    private Competition competition;
    private Cyclist cyclist;
    private GeneralResultId generalResultId;
    private Team team;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        competition = new Competition();
        competition.setId(1L);

        team = new Team();
        team.setId(1L);
        cyclist = new Cyclist("John", "Doe", "USA", LocalDate.of(2000, 5, 19), team);

    }

    @Test
    public void testInscrireCycliste() {
        generalResultId = new GeneralResultId();
        generalResultId.setCompetitionId(competition.getId());
        generalResultId.setCyclistId(cyclist.getId());

        service.inscrireCycliste(competition, cyclist);
        verify(repo, times(1)).save(any(GeneralResult.class));
    }

    @Test
    public void testConsulterInscrits() {
        when(repo.findAllByCompetitionId(competition.getId()))
                .thenReturn(Collections.singletonList(new GeneralResult()));

        List<Cyclist> inscrits = service.consulterInscrits(competition.getId());

        assertEquals(1, inscrits.size());
    }
}