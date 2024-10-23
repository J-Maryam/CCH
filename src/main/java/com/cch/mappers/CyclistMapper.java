package com.cch.mappers;

import com.cch.dtos.cyclist.request.CyclistRequestDTO;
import com.cch.dtos.cyclist.response.CyclistResponseDTO;
import com.cch.entities.Cyclist;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CyclistMapper extends GenericMapper<Cyclist, CyclistRequestDTO, CyclistResponseDTO> {

}
