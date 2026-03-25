package com.springboot.weather_monitoring.repositories;

import com.springboot.weather_monitoring.domains.entities.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData , UUID> {
}
