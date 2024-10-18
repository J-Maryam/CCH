package com.cch.services;

import com.cch.entities.Competition;
import org.springframework.stereotype.Service;

public interface CompetitionService {
    Competition saveCompetition(Competition competition);
}
