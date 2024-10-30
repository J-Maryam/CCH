package com.cch.dtos.response;

import java.util.Set;


public record TeamResponseDTO(
        Long id,
        String team,
        Set<CyclistDTO> cyclists
) {}

