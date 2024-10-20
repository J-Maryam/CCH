package com.cch.services.Impl;

import com.cch.entities.Stage;
import com.cch.repositories.StageRepository;
import com.cch.services.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Service
public class StageServiceImpl implements StageService {

    @Autowired
    private StageRepository stageRepository;

    @Override
    public Stage saveStage(Stage stage) {
        if (stage.getNumber() <= 0) {
            throw new IllegalArgumentException("Le numéro du stage doit être positif.");
        }
        if (stage.getStartLocation().equalsIgnoreCase(stage.getEndLocation())) {
            throw new IllegalArgumentException("Les lieux de départ et d'arrivée doivent être différents.");
        }
//        if (stage.getDate().isBefore(LocalDate.now())) {
//            throw new IllegalArgumentException("La date du stage doit être dans le futur.");
//        }
        if (stage.getStartTime() == null) {
            throw new IllegalArgumentException("L'heure de départ doit être spécifiée.");
        }
        if (stage.getCompetition() == null) {
            throw new IllegalArgumentException("La compétition ne doit pas être nulle.");
        }
        return stageRepository.save(stage);
    }

    @Override
    public List<Stage> getStages() {
        return stageRepository.findAll();
    }
}
