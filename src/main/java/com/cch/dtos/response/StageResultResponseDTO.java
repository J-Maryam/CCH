package com.cch.dtos.response;

import java.time.Duration;

public record StageResultResponseDTO(
        CyclistResponseDTO cyclist,
        StageResponseDTO stage,
        Duration time,
        Integer rank
) {}



