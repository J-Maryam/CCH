package com.cch.mappers;

import com.cch.dtos.cyclist.request.StageResultRequestDTO;
import com.cch.dtos.cyclist.response.StageResultResponseDTO;
import com.cch.entities.StageResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StageResultMapper extends GenericMapper<StageResult, StageResultRequestDTO, StageResultResponseDTO> {

}
