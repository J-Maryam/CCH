package com.cch.services.Impl;

import com.cch.entities.Stage;
import com.cch.repositories.StageRepository;
import com.cch.services.StageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Stage> getStageById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID ne peut pas être null");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("ID ne peut pas être négatif");
        }
        return stageRepository.findById(id);
    }

    @Override
    public Stage updateStage(Stage stage) {
        if (stage == null) {
            throw new IllegalArgumentException("Stage ne peut pas être null");
        }

        if (stage.getId() == null || stage.getId() <= 0) {
            throw new IllegalArgumentException("ID de stage ne peut pas être null ou négatif");
        }

        if (!stageRepository.existsById(stage.getId())) {
            throw new EntityNotFoundException("Stage non trouvé avec ID " + stage.getId());
        }
        return stageRepository.save(stage);
    }

    @Override
    public void deleteStage(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID ne peut pas être null");
        }

        if (id <= 0) {
            throw new IllegalArgumentException("ID ne peut pas être négatif ou nul");
        }

        if (!stageRepository.existsById(id)) {
            throw new EntityNotFoundException("Stage non trouvé avec ID " + id);
        }
        stageRepository.deleteById(id);
    }

}
