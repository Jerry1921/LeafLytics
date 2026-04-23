package com.leaflytics.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a plant that the user is caring for.
 *
 * wateringIntervalDays – how often this plant needs water (default 7).
 * The frontend uses this to compute "next watering date" and health status.
 */
@Entity
@Table(name = "plants")
@Data                   // Lombok: getters, setters, toString, equals, hashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Plant name is required")
    @Column(nullable = false, length = 120)
    private String name;

    /** e.g. "Tropical", "Succulent", "Fern", "Herb" */
    @Column(length = 80)
    private String type;

    /** e.g. "Living Room", "Balcony" */
    @Column(length = 120)
    private String location;

    @Column(columnDefinition = "TEXT")
    private String notes;

    private LocalDate lastWateredDate;
    private LocalDate lastFertilizedDate;

    /**
     * How many days between waterings.
     * Defaults differ by plant type – the user can override this value.
     */
    @Column(nullable = false)
    @Builder.Default
    private int wateringIntervalDays = 7;

    /** Record creation timestamp – set automatically, never updated. */
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /** Last-modified timestamp – updated on every save. */
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt  = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
