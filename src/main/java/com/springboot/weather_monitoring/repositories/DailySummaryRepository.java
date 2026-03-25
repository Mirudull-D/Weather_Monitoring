package com.springboot.weather_monitoring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DailySummaryRepository extends JpaRepository<DailySummaryRepository, UUID> {
}
