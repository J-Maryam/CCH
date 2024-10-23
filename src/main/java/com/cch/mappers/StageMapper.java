package com.cch.mappers;

import com.cch.dtos.cyclist.request.StageRequestDTO;
import com.cch.dtos.cyclist.response.StageResponseDTO;
import com.cch.entities.Stage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StageMapper extends GenericMapper<Stage, StageRequestDTO, StageResponseDTO> {

}
