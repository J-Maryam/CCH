package com.cch.services.Impl;

import com.cch.entities.Cyclist;
import com.cch.repositories.CyclistRepository;
import com.cch.services.CyclistService;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
public class CyclistServiceImpl implements CyclistService {

    private final CyclistRepository cyclistRepository;


    public CyclistServiceImpl(CyclistRepository cyclistRepository) {
        this.cyclistRepository = cyclistRepository;
    }

    @Override
    public Cyclist save(Cyclist cyclist) {

        if (cyclist.getFName() == null || cyclist.getFName().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        if (cyclist.getLName() == null || cyclist.getLName().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        if (cyclist.getBirthDate() == null || cyclist.getBirthDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birth date must be in the past");
        }
        if (cyclist.getTeam() == null) {
            throw new IllegalArgumentException("Cyclist must belong to a team");
        }
        return cyclistRepository.save(cyclist);
    }

    @Override
    public List<Cyclist> findAll() {
        return cyclistRepository.findAll();
    }

    @Override
    public Optional<Cyclist> findById(Long id) {
        return cyclistRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        cyclistRepository.deleteById(id);
    }

}