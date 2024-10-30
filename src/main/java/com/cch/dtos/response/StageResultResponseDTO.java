package com.cch.dtos.response;

import java.time.Duration;

public record StageResultResponseDTO(
        CyclistDTO cyclist,
        StageDTO stage,
        Duration time,
        Integer rank
) {}
