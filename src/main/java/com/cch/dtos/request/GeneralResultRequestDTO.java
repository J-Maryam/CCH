package com.cch.dtos.request;

import com.cch.entities.embeddebals.GeneralResultId;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;

public record GeneralResultRequestDTO(
        @NotNull GeneralResultId id,
        Duration generalTime,
        Integer generalRank
) {}