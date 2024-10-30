package com.cch.entities;

import com.cch.entities.embeddebals.StageResultId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Duration;

@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
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

    @NotNull
    private Duration time;
    private Integer rank;

    public StageResult(Cyclist cyclist, Stage stage, Duration time) {
        this.id = new StageResultId(cyclist.getId(), stage.getId());
        this.cyclist = cyclist;
        this.stage = stage;
        this.time = time;
    }
}