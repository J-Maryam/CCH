package com.cch.repositories;

import com.cch.entities.Cyclist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CyclistRepository extends JpaRepository<Cyclist, Long> {

}