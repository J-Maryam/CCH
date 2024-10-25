package com.cch.dtos.cyclist.response;

import java.time.Duration;

public record StageResultResponseDTO(
        CyclistResponseDTO cyclist,
        StageResponseDTO stage,
        Duration time,
        Integer rank
) {}



