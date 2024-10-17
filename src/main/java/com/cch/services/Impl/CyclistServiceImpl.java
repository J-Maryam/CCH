package com.cch.services.Impl;

import com.cch.entities.Cyclist;
import com.cch.repositories.CyclistRepository;
import com.cch.services.CyclistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CyclistServiceImpl implements CyclistService {

    private final CyclistRepository cyclistRepository;

    @Autowired
    public CyclistServiceImpl(CyclistRepository cyclistRepository) {
        this.cyclistRepository = cyclistRepository;
    }

    @Override
    @Transactional
    public Cyclist save(Cyclist cyclist) {
        return cyclistRepository.save(cyclist);
    }
}