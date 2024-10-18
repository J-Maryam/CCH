package com.cch.services.Impl;

import com.cch.entities.Competition;
import com.cch.repositories.CompetitionRepository;
import com.cch.services.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class CompetitionServiceImpl implements CompetitionService {

    @Autowired
    private CompetitionRepository competitionRepository;

    @Override
    public Competition saveCompetition(Competition competition) {
        if (competition.getName() == null || competition.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null");
        }

        if (competition.getEndDate().isBefore(competition.getStartDate())) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }

        if (competition.getStartDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Start date must be in the future");
        }

        return competitionRepository.save(competition);    }

    @Override
    public Optional<Competition> findCompetitionById(Long id) {
        return competitionRepository.findById(id);
    }
}