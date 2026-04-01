package com.springboot.weather_monitoring.domains.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailySummaryDto {
    private LocalDate date;
    private double avgtemp;
    private  double maxtemp;
    private  double mintemp;
}
