package com.cch.services.Impl;

import com.cch.entities.Cyclist;
import com.cch.repositories.CyclistRepository;
import com.cch.services.CyclistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CyclistServiceImpl implements CyclistService {

    @Autowired
    private CyclistRepository cyclistRepository;

    @Override
    public Cyclist save(Cyclist cyclist) {
        return cyclistRepository.save(cyclist);
    }
}