package com.springboot.weather_monitoring.repositories;

import com.springboot.weather_monitoring.domains.dtos.DailySummaryDto;
import com.springboot.weather_monitoring.domains.entities.DailySummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DailySummaryRepository extends JpaRepository<DailySummary, UUID> {
    @Query("""
        SELECT 
            DATE(w.recordedAt), 
            AVG(w.temperature), 
            MAX(w.temperature), 
            MIN(w.temperature) 
        FROM WeatherData w 
        WHERE w.location.cityName = :city 
        GROUP BY DATE(w.recordedAt) 
        ORDER BY DATE(w.recordedAt) DESC
    """)
    List<Object[]> getDailySummaryRaw(@Param("city") String city);
}
