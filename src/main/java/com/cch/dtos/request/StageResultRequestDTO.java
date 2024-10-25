package com.cch.dtos.request;

import jakarta.validation.constraints.NotNull;

import java.time.Duration;

public record StageResultRequestDTO(
        @NotNull Long cyclistId,
        @NotNull Long stageId,
        @NotNull Duration time,
        @NotNull Integer rank
) {}

