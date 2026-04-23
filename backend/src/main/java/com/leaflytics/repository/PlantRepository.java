package com.leaflytics.repository;

import com.leaflytics.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data repository for Plant.
 * All basic CRUD (save, findById, findAll, delete) is provided automatically.
 */
@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

    /** Find all plants whose type matches (case-sensitive). */
    List<Plant> findByType(String type);
}
