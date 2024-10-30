package com.cch.entities;


import com.cch.entities.embeddebals.GeneralResultId;
import com.cch.services.GeneralResultService;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

public GeneralResult(Cyclist cyclist, Competition competition) {
    this.id = new GeneralResultId(competition.getId(), cyclist.getId());
    this.competition = competition;
    this.cyclist = cyclist;
}

}
