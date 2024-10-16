package com.cch.services.Impl;

import com.cch.entities.Cyclist;
import com.cch.repositories.CyclistRepository;
import com.cch.services.CyclistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

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