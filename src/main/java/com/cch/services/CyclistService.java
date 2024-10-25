package com.cch.services;

import com.cch.dtos.request.CyclistRequestDTO;
import com.cch.dtos.response.CyclistResponseDTO;
import com.cch.entities.Cyclist;
import com.cch.entities.Team;

import java.util.List;

public interface CyclistService extends GenericService<Cyclist, Long, CyclistRequestDTO, CyclistResponseDTO>{
    List<CyclistResponseDTO> findAllSortedByLastName(String lName);
    List<CyclistResponseDTO> findAllSortedByNationality(String nationality);
    List<CyclistResponseDTO> findAllSortedByTeam(Team team);
}
