package com.cch.controllers;

import com.cch.dtos.request.GeneralResultRequestDTO;
import com.cch.dtos.request.TeamRequestDTO;
import com.cch.dtos.response.GeneralResultResponseDTO;
import com.cch.dtos.response.TeamResponseDTO;
import com.cch.entities.embeddebals.GeneralResultId;
import com.cch.services.GeneralResultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/general-results")
@RequiredArgsConstructor
public class GeneralResultController {
    private final GeneralResultService generalResultService;

    @GetMapping
    public ResponseEntity<List<GeneralResultResponseDTO>> getAll() {
        return new ResponseEntity<>(generalResultService.consulterInscrits(), HttpStatus.OK);
    }

    @GetMapping("/{competitionId}/{cyclistId}")
    public ResponseEntity<GeneralResultResponseDTO> getById(@PathVariable("competitionId") Long competitionId, @PathVariable("cyclistId") Long cyclistId) {
        GeneralResultResponseDTO result = generalResultService.getGeneralResultById(new GeneralResultId(competitionId, cyclistId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GeneralResultResponseDTO> create(@Valid @RequestBody GeneralResultRequestDTO requestDTO) {
        GeneralResultResponseDTO newResult = generalResultService.inscrireCycliste(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newResult);
    }

    @PutMapping("/{competitionId}/{cyclistId}")
    public ResponseEntity<GeneralResultResponseDTO> updateGeneralResult(@PathVariable("competitionId") Long competitionId, @PathVariable("cyclistId") Long cyclistId, @RequestBody GeneralResultRequestDTO requestDTO) {
        GeneralResultResponseDTO updateResult = generalResultService.updateGeneralResult(new GeneralResultId(competitionId, cyclistId), requestDTO);
        return new ResponseEntity<>(updateResult, HttpStatus.OK);
    }

    @DeleteMapping("/{competitionId}/{cyclistId}")
    public ResponseEntity<Void> delete(@PathVariable("competitionId") Long competitionId, @PathVariable("cyclistId") Long cyclistId) {
        generalResultService.retirerCycliste(new GeneralResultId(competitionId, cyclistId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
