package com.cch.mappers;

import com.cch.dtos.request.CompetitionRequestDTO;
import com.cch.dtos.response.CompetitionResponseDTO;
import com.cch.entities.Competition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompetitionMapper extends GenericMapper<Competition, CompetitionRequestDTO, CompetitionResponseDTO> {

}
