package com.cch.services;

import com.cch.entities.Cyclist;

import java.util.List;

public interface CyclistService {
    Cyclist save(Cyclist cyclist);
    List<Cyclist> findAll();
}
