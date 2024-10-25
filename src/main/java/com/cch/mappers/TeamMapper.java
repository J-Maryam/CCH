package com.cch.mappers;

import com.cch.dtos.request.TeamRequestDTO;
import com.cch.dtos.response.TeamResponseDTO;
import com.cch.entities.Team;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper extends GenericMapper<Team, TeamRequestDTO, TeamResponseDTO> {

}
