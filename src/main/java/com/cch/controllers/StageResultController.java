package com.cch.controllers;

import com.cch.dtos.request.StageResultRequestDTO;
import com.cch.dtos.response.StageResultResponseDTO;
import com.cch.entities.embeddebals.StageResultId;
import com.cch.services.StageResultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stage-results")
@RequiredArgsConstructor
public class StageResultController {

    private final StageResultService stageResultService;

    @GetMapping
    public ResponseEntity<List<StageResultResponseDTO>> getAll() {
        return new ResponseEntity<>(stageResultService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{cyclistId}/{stageId}")
    public ResponseEntity<StageResultResponseDTO> getById(@PathVariable("cyclistId") Long cyclistId, @PathVariable("stageId") Long stageId) {
        return stageResultService.getById(new StageResultId(cyclistId, stageId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StageResultResponseDTO> create(@Valid @RequestBody StageResultRequestDTO requestDTO) {
        StageResultResponseDTO newResult = stageResultService.save(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newResult);
    }

    @PutMapping("/{cyclistId}/{stageId}")
    public ResponseEntity<StageResultResponseDTO> update(@PathVariable("cyclistId") Long cyclistId, @PathVariable("stageId") Long stageId, @RequestBody StageResultRequestDTO requestDTO) {
        StageResultResponseDTO updatedResult = stageResultService.update(new StageResultId(cyclistId, stageId), requestDTO);
        return ResponseEntity.ok(updatedResult);
    }

    @DeleteMapping("/{cyclistId}/{stageId}")
    public ResponseEntity<Void> delete(@PathVariable("cyclistId") Long cyclistId, @PathVariable("stageId") Long stageId) {
        stageResultService.delete(new StageResultId(cyclistId, stageId));
        return ResponseEntity.noContent().build();
    }
}