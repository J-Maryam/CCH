package com.cch.services.Impl;

import com.cch.entities.Competition;
import com.cch.entities.Cyclist;
import com.cch.entities.GeneralResult;
import com.cch.entities.embeddebals.GeneralResultId;
import com.cch.repositories.GeneralResultRepository;
import com.cch.services.GeneralResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GeneralResultServiceImpl implements GeneralResultService {

    @Autowired
    private GeneralResultRepository generalResultRepository;

    @Override
    public void inscrireCycliste(Competition competition, Cyclist cyclist) {
        GeneralResult generalResult = new GeneralResult();
        GeneralResultId id = new GeneralResultId();
        id.setCompetitionId(competition.getId());
        id.setCyclistId(cyclist.getId());
        generalResult.setId(id);
        generalResult.setCompetition(competition);
        generalResult.setCyclist(cyclist);

        generalResultRepository.save(generalResult);
    }
}
