package com.cch.dtos.cyclist.response;

import java.util.Set;

public record TeamResponseDTO(
        Long id,
        String team,
        Set<CyclistResponseDTO> cyclists
) {}

