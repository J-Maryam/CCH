package com.cch.dtos.request;

import jakarta.validation.constraints.NotNull;

import java.time.Duration;

public record GeneralResultRequestDTO(
        @NotNull Long cyclistId,
        @NotNull Long competitionId,
        @NotNull Duration generalTime,
        @NotNull Integer generalRank
) {}