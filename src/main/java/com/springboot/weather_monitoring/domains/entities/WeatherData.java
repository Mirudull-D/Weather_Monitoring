package com.springboot.weather_monitoring.domains.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "weather_datas")
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    private Double temperature;
    private Double humidity;
    private Double pressure;
    private Double windSpeed;

    @Column(nullable = false)
    private LocalDateTime recordedAt;
}
