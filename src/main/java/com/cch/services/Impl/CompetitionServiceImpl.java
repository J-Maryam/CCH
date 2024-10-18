package com.cch.services.Impl;

import com.cch.entities.Competition;
import com.cch.repositories.CompetitionRepository;
import com.cch.services.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompetitionServiceImpl implements CompetitionService {

    @Autowired
    private CompetitionRepository competitionRepository;

    @Override
    public Competition saveCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }
}
