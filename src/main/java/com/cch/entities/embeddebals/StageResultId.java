package com.cch.entities.embeddebals;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class StageResultId implements Serializable {

    private Long cyclistId;
    private Long stageId;
}
