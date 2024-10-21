package com.cch.services;

import com.cch.entities.Competition;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CompetitionService {
    Competition saveCompetition(Competition competition);
    Optional<Competition> findCompetitionById(Long id);
    Competition updateCompetition(Competition competition);
    void deleteCompetition(Long id);
    List<Competition> findAllCompetitions();
}
