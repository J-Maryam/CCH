package com.cch.entities.embeddebals;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class StageResultId implements Serializable {

    private Long cyclistId;
    private Long stageId;
}
