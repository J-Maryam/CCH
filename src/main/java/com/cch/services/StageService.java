package com.cch.services;

import com.cch.entities.Stage;

import java.util.List;

public interface StageService {
    Stage saveStage(Stage stage);
    List<Stage> getStages();
}
