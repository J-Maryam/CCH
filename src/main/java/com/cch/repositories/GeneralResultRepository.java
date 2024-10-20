package com.cch.repositories;

import com.cch.entities.GeneralResult;
import com.cch.entities.embeddebals.GeneralResultId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralResultRepository extends JpaRepository<GeneralResult, GeneralResultId> {

}
