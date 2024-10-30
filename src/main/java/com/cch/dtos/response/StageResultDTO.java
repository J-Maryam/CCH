package com.cch.dtos.response;

import java.time.Duration;

public record StageResultDTO(
        CyclistDTO cyclist,
        Duration time,
        Integer rank
) {}