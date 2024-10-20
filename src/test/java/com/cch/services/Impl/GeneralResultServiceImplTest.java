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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    }

    @Test
    public void testInscrireCycliste() {
        competition = new Competition();
        competition.setId(1L);

        team = new Team();
        team.setId(1L);
        cyclist = new Cyclist("John", "Doe", "USA", LocalDate.of(2000, 5, 19), team);

        generalResultId = new GeneralResultId();
        generalResultId.setCompetitionId(competition.getId());
        generalResultId.setCyclistId(cyclist.getId());

        service.inscrireCycliste(competition, cyclist);
        verify(repo, times(1)).save(any(GeneralResult.class));
    }
}