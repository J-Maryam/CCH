package com.cch.repositories;

import com.cch.entities.StageResult;
import com.cch.entities.embeddebals.StageResultId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageResultRepository extends JpaRepository<StageResult, StageResultId> {
}
