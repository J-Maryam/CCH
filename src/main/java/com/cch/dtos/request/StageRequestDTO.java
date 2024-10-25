package com.cch.dtos.request;

import com.cch.entities.enums.StageType;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record StageRequestDTO(
        @NotNull Integer number,
        @NotNull String startLocation,
        @NotNull String endLocation,
        @NotNull LocalDate date,
        @NotNull LocalTime startTime,
        @NotNull StageType stageType,
        @NotNull Long competitionId
) {}


