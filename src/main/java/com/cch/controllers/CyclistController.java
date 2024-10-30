package com.cch.controllers;

import com.cch.dtos.request.CyclistRequestDTO;
import com.cch.dtos.response.CyclistResponseDTO;
import com.cch.services.CyclistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cyclists")
@RequiredArgsConstructor
public class CyclistController {

    private final CyclistService cyclistService;

    @GetMapping
    public ResponseEntity<List<CyclistResponseDTO>> getAllCyclists() {
        List<CyclistResponseDTO> cyclists = cyclistService.getAll();
        return ResponseEntity.ok(cyclists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CyclistResponseDTO> getCyclistById(@PathVariable("id") Long id) {
        return cyclistService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CyclistResponseDTO> createCyclist(@Valid @RequestBody CyclistRequestDTO cyclistRequestDTO) {
        CyclistResponseDTO newCyclist = cyclistService.save(cyclistRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCyclist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CyclistResponseDTO> updateCyclist(@PathVariable("id") Long id, @Valid @RequestBody CyclistRequestDTO cyclistRequestDTO) {
        Optional<CyclistResponseDTO> existingCyclist = cyclistService.getById(id);
        if (existingCyclist.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        CyclistResponseDTO updatedCyclist = cyclistService.update(id, cyclistRequestDTO);
        return ResponseEntity.ok(updatedCyclist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCyclist(@PathVariable("id") Long id) {
        Optional<CyclistResponseDTO> existingCyclist = cyclistService.getById(id);
        if (existingCyclist.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        cyclistService.delete(id);
        return ResponseEntity.noContent().build();
    }
}