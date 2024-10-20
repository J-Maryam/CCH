package com.cch.entities;

import com.cch.entities.embeddebals.StageResultId;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Duration;

@Data
@NoArgsConstructor
@Entity
public class StageResult {

    @EmbeddedId
    private StageResultId id;

    @ManyToOne
    @MapsId("cyclistId")
    @JoinColumn(name = "cyclist_id")
    private Cyclist cyclist;

    @ManyToOne
    @MapsId("stageId")
    @JoinColumn(name = "stage_id")
    private Stage stage;

    private Duration time;
    private Integer rank;

    public StageResult(Cyclist cyclist, Stage stage, Duration time, Integer rank) {
        this.cyclist = cyclist;
        this.stage = stage;
        this.time = time;
        this.rank = rank;
    }
}