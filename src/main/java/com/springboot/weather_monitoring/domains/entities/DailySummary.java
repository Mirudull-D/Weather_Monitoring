package com.springboot.weather_monitoring.domains.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "daily_summary")
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailySummary {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(name = "summary_date", nullable = false)
    private LocalDate summaryDate;

    private Double avgTemp;
    private Double maxTemp;
    private Double minTemp;
}
