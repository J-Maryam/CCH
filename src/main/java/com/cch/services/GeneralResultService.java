package com.cch.services;

import com.cch.entities.Competition;
import com.cch.entities.Cyclist;

public interface GeneralResultService {
    void inscrireCycliste(Competition competition, Cyclist cyclist);
}
