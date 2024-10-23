package com.cch.dtos.cyclist.request;

import jakarta.validation.constraints.NotNull;

import java.time.Duration;

public record StageResultRequestDTO(
        @NotNull Long cyclistId,
        @NotNull Long stageId,
        @NotNull Duration time,
        @NotNull Integer rank
) {}

