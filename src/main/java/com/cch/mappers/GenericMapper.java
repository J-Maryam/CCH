package com.cch.mappers;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenericMapper<Entity, RequestDTO, ResponseDTO> {
    ResponseDTO toResponseDto(Entity entity);
    Entity toEntity(RequestDTO requestDTO);
}