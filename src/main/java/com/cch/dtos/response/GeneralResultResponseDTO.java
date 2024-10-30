package com.cch.dtos.response;

import java.time.Duration;

public record GeneralResultResponseDTO(
        CyclistDTO cyclist,
        CompetitionDTO competition,
        Duration generalTime,
        Integer generalRank
) {}