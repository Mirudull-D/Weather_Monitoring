package com.springboot.weather_monitoring.domains.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherResponseDto {

    private String cityName;
    private double temperature;
    private double humidity;
    private double windSpeed;


}
