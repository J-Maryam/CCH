package com.cch.entities;

import com.cch.entities.enums.StageType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Stage {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull
    private Integer number;

    @NotNull
    private String startLocation;

    @NotNull
    private String endLocation;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime startTime;

    @Enumerated(EnumType.STRING)
    private StageType stageType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @OneToMany(mappedBy = "stage", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StageResult> results = new HashSet<>();
}
