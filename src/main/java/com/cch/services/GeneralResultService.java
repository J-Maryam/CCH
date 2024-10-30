package com.cch.services;

import com.cch.dtos.request.GeneralResultRequestDTO;
import com.cch.dtos.response.CyclistResponseDTO;
import com.cch.dtos.response.GeneralResultResponseDTO;
import com.cch.entities.GeneralResult;
import com.cch.entities.embeddebals.GeneralResultId;

import java.util.List;

public interface GeneralResultService {
    GeneralResultResponseDTO inscrireCycliste(GeneralResultRequestDTO requestDTO);
    List<GeneralResultResponseDTO> consulterInscrits();
    GeneralResultResponseDTO getGeneralResultById(GeneralResultId id);
    GeneralResultResponseDTO updateGeneralResult(GeneralResultId id, GeneralResultRequestDTO requestDTO) ;
    void retirerCycliste(GeneralResultId id);
}
