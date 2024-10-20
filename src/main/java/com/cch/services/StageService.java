package com.cch.services;

import com.cch.entities.Stage;

import java.util.List;
import java.util.Optional;

public interface StageService {
    Stage saveStage(Stage stage);
    List<Stage> getStages();
    Optional<Stage> getStageById(Long id);
    Stage updateStage(Stage stage);
    void deleteStage(Long id);
}
