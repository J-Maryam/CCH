package com.cch.entities;


import com.cch.entities.embeddebals.GeneralResultId;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@Entity
@NoArgsConstructor
public class GeneralResult {

    @EmbeddedId
    private GeneralResultId id;

    @ManyToOne
    @MapsId("cyclistId")
    @JoinColumn(name = "cyclist_id")
    private Cyclist cyclist;

    @ManyToOne
    @MapsId("competitionId")
    @JoinColumn(name = "competition_id")
    private Competition competition;

    private Duration generalTime;
    private Integer generalRank;
}
