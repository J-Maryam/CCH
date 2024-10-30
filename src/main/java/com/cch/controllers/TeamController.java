package com.cch.controllers;

import com.cch.dtos.request.TeamRequestDTO;
import com.cch.dtos.response.TeamResponseDTO;
import com.cch.services.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<List<TeamResponseDTO>> getAllTeams() {
//        List<TeamResponseDTO> teams = teamService.getAll();
//        return ResponseEntity.ok(teams);
        return new ResponseEntity<>(teamService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponseDTO> getTeamById(@PathVariable("id") Long id) {
        Optional<TeamResponseDTO> team = teamService.getById(id);
        return team.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TeamResponseDTO> createTeam(@Valid @RequestBody TeamRequestDTO teamRequestDTO) {
        TeamResponseDTO newTeam = teamService.save(teamRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTeam);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamResponseDTO> updateTeam(@PathVariable("id") Long id, @Valid @RequestBody TeamRequestDTO teamRequestDTO) {
        Optional<TeamResponseDTO> team = teamService.getById(id);
        if (team.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        TeamResponseDTO updateTeam = teamService.update(id, teamRequestDTO);
        return ResponseEntity.ok(updateTeam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable("id") Long id) {
        Optional<TeamResponseDTO> team = teamService.getById(id);
        if (team.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        teamService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
