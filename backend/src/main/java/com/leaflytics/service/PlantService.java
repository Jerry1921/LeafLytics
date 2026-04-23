package com.leaflytics.service;

import com.leaflytics.entity.Plant;
import com.leaflytics.repository.PlantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business logic for plant management.
 * Controllers delegate all database work to this service.
 */
@Service
@RequiredArgsConstructor   // Lombok injects final fields via constructor
public class PlantService {

    private final PlantRepository plantRepository;

    // ── Read ─────────────────────────────────────────────────────────────────

    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    public Plant getPlantById(Long id) {
        return plantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plant not found with id: " + id));
    }

    // ── Create ────────────────────────────────────────────────────────────────

    public Plant createPlant(Plant plant) {
        // Apply type-based default watering interval if client didn't supply one
        if (plant.getWateringIntervalDays() <= 0) {
            plant.setWateringIntervalDays(defaultInterval(plant.getType()));
        }
        return plantRepository.save(plant);
    }

    // ── Update ────────────────────────────────────────────────────────────────

    public Plant updatePlant(Long id, Plant updated) {
        Plant existing = getPlantById(id);

        existing.setName(updated.getName());
        existing.setType(updated.getType());
        existing.setLocation(updated.getLocation());
        existing.setNotes(updated.getNotes());
        existing.setLastWateredDate(updated.getLastWateredDate());
        existing.setLastFertilizedDate(updated.getLastFertilizedDate());

        // Allow changing the watering interval
        if (updated.getWateringIntervalDays() > 0) {
            existing.setWateringIntervalDays(updated.getWateringIntervalDays());
        }

        return plantRepository.save(existing);
    }

    // ── Delete ────────────────────────────────────────────────────────────────

    public void deletePlant(Long id) {
        if (!plantRepository.existsById(id)) {
            throw new RuntimeException("Plant not found with id: " + id);
        }
        plantRepository.deleteById(id);
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    /**
     * Returns a sensible default watering interval based on plant type.
     * Succulents and cacti need water much less often than tropical plants.
     */
    private int defaultInterval(String type) {
        if (type == null) return 7;
        return switch (type.toLowerCase()) {
            case "succulent", "cactus" -> 14;
            case "tropical"            -> 5;
            case "herb"                -> 3;
            case "fern"                -> 4;
            default                    -> 7;
        };
    }
}
