package com.springboot.weather_monitoring.controllers;

import com.springboot.weather_monitoring.domains.dtos.WeatherRequestDto;
import com.springboot.weather_monitoring.domains.dtos.WeatherResponseDto;
import com.springboot.weather_monitoring.domains.entities.WeatherData;
import com.springboot.weather_monitoring.mappers.WeatherMapper;
import com.springboot.weather_monitoring.repositories.LocationRepository;
import com.springboot.weather_monitoring.services.WeatherDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/weather")
@RequiredArgsConstructor
public class WeatherDataController {

    public final WeatherDataService weatherDataService;
    public final LocationRepository locationRepository;
    private final WeatherMapper weatherMapper;


    @PostMapping
    public ResponseEntity<WeatherResponseDto> getLiveWeatherData(
            @RequestBody WeatherRequestDto weatherRequestDto
            ){

        WeatherData weatherData = weatherDataService.fetchAndSaveWeatherData(weatherRequestDto);

        WeatherResponseDto weatherResponseDto = weatherMapper.toDto(weatherData);

        return ResponseEntity.ok(weatherResponseDto);
    }

}
