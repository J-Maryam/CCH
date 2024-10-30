package com.cch.mappers;

import com.cch.dtos.request.StageResultRequestDTO;
import com.cch.dtos.response.StageResultResponseDTO;
import com.cch.entities.StageResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StageResultMapper extends GenericMapper<StageResult, StageResultRequestDTO, StageResultResponseDTO> {

}