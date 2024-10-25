package com.cch.services;

import com.cch.dtos.request.GeneralResultRequestDTO;
import com.cch.dtos.response.CyclistResponseDTO;
import com.cch.dtos.response.GeneralResultResponseDTO;
import com.cch.entities.GeneralResult;
import com.cch.entities.embeddebals.GeneralResultId;

import java.util.List;

public interface GeneralResultService extends GenericService<GeneralResult, GeneralResultId, GeneralResultRequestDTO, GeneralResultResponseDTO>{
    GeneralResultResponseDTO inscrireCycliste(GeneralResultRequestDTO requestDTO);
    List<CyclistResponseDTO> consulterInscrits(Long competitionId);
    void retirerCycliste(GeneralResultId generalResultId);
}
