package com.cch.mappers;

import com.cch.dtos.request.GeneralResultRequestDTO;
import com.cch.dtos.response.GeneralResultResponseDTO;
import com.cch.entities.GeneralResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GeneralResultMapper extends GenericMapper<GeneralResult, GeneralResultRequestDTO, GeneralResultResponseDTO> {

}
