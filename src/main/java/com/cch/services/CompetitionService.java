package com.cch.services;


import com.cch.dtos.request.CompetitionRequestDTO;
import com.cch.dtos.response.CompetitionResponseDTO;
import com.cch.entities.Competition;

public interface CompetitionService extends GenericService<Competition, Long, CompetitionRequestDTO, CompetitionResponseDTO> {

}
