package com.cch.services;

import com.cch.entities.Competition;
import com.cch.entities.Cyclist;
import com.cch.entities.GeneralResult;

import java.util.List;

public interface GeneralResultService {
    void inscrireCycliste(Competition competition, Cyclist cyclist);
    List<Cyclist> consulterInscrits(Long competitionId);
}
