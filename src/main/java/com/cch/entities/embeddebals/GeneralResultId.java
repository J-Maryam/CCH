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
public class GeneralResultId implements Serializable {

    private Long competitionId;
    private Long cyclistId;

}
