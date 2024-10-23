package com.cch.mappers;

import com.cch.dtos.cyclist.request.CompetitionRequestDTO;
import com.cch.dtos.cyclist.response.CompetitionResponseDTO;
import com.cch.entities.Competition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompetitionMapper extends GenericMapper<Competition, CompetitionRequestDTO, CompetitionResponseDTO> {

}
