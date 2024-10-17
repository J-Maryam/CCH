package com.cch.entities.embeddebals;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class GeneralResultId implements Serializable {

    private Long competitionId;
    private Long cyclistId;

}
