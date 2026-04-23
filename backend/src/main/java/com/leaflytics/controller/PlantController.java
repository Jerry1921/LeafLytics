package com.leaflytics.controller;

import com.leaflytics.entity.Plant;
import com.leaflytics.service.PlantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller for plant CRUD operations.
 *
 * Base URL: /api/plants
 */
@RestController
@RequestMapping("/api/plants")
@RequiredArgsConstructor
public class PlantController {

    private final PlantService plantService;

    // ── GET /api/plants ──────────────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<List<Plant>> getAllPlants() {
        return ResponseEntity.ok(plantService.getAllPlants());
    }

    // ── GET /api/plants/{id} ─────────────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<Plant> getPlant(@PathVariable Long id) {
        return ResponseEntity.ok(plantService.getPlantById(id));
    }

    // ── POST /api/plants ─────────────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<Plant> createPlant(@Valid @RequestBody Plant plant) {
        Plant saved = plantService.createPlant(plant);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // ── PUT /api/plants/{id} ─────────────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<Plant> updatePlant(
            @PathVariable Long id,
            @Valid @RequestBody Plant plant) {
        return ResponseEntity.ok(plantService.updatePlant(id, plant));
    }

    // ── DELETE /api/plants/{id} ──────────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletePlant(@PathVariable Long id) {
        plantService.deletePlant(id);
        return ResponseEntity.ok(Map.of("message", "Plant deleted successfully"));
    }

    // ── Global error handler for this controller ─────────────────────────────
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(Map.of("error", ex.getMessage()));
    }
}
