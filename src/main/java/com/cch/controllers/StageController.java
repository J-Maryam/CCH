package com.cch.controllers;

import com.cch.dtos.request.StageRequestDTO;
import com.cch.dtos.response.StageResponseDTO;
import com.cch.services.StageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/stages")
@RequiredArgsConstructor
public class StageController {

    private final StageService stageService;

    @GetMapping
    public ResponseEntity<List<StageResponseDTO>> getAll() {
        return new ResponseEntity<>(stageService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StageResponseDTO> getById(@PathVariable("id") Long id) {
        return stageService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StageResponseDTO> createCyclist(@Valid @RequestBody StageRequestDTO stageRequestDTO) {
        StageResponseDTO newStage = stageService.save(stageRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StageResponseDTO> updateCyclist(@PathVariable("id") Long id, @Valid @RequestBody StageRequestDTO stageRequestDTO) {
        Optional<StageResponseDTO> existingStage = stageService.getById(id);
        if (existingStage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        StageResponseDTO updateStage = stageService.update(id, stageRequestDTO);
        return ResponseEntity.ok(updateStage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCyclist(@PathVariable("id") Long id) {
        Optional<StageResponseDTO> existingStage = stageService.getById(id);
        if (existingStage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        stageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}