package com.cch.dtos.request;

import com.cch.entities.embeddebals.StageResultId;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;

public record StageResultRequestDTO(
        @NotNull StageResultId id,
        @NotNull Duration time,
        Integer rank
) {}

