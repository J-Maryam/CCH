package com.cch.controllers;

import com.cch.dtos.request.CompetitionRequestDTO;
import com.cch.dtos.request.CyclistRequestDTO;
import com.cch.dtos.response.CompetitionResponseDTO;
import com.cch.dtos.response.CyclistResponseDTO;
import com.cch.services.CompetitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/competitions")
@RequiredArgsConstructor
public class CompetitionController {

    private final CompetitionService competitionService;

    @GetMapping
    public ResponseEntity<List<CompetitionResponseDTO>> getAll() {
        return new ResponseEntity<>(competitionService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetitionResponseDTO> getById(@PathVariable("id") Long id) {
        return competitionService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CompetitionResponseDTO> create(@Valid @RequestBody CompetitionRequestDTO requestDTO) {
        CompetitionResponseDTO newCompetition = competitionService.save(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCompetition);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetitionResponseDTO> update(@PathVariable("id") Long id, @Valid @RequestBody CompetitionRequestDTO requestDTO) {
        Optional<CompetitionResponseDTO> existingCompetition = competitionService.getById(id);
        if (existingCompetition.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        CompetitionResponseDTO updatedCompetition = competitionService.update(id, requestDTO);
        return ResponseEntity.ok(updatedCompetition);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional<CompetitionResponseDTO> existingCyclist = competitionService.getById(id);
        if (existingCyclist.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        competitionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}