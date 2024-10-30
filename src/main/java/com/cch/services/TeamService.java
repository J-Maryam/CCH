package com.cch.services;

import com.cch.dtos.request.TeamRequestDTO;
import com.cch.dtos.response.TeamResponseDTO;
import com.cch.entities.Team;

public interface TeamService extends GenericService<Team, Long, TeamRequestDTO, TeamResponseDTO>{
}
