package com.springboot.weather_monitoring.controllers;

import com.springboot.weather_monitoring.domains.dtos.WeatherRequestDto;
import com.springboot.weather_monitoring.domains.dtos.WeatherResponseDto;
import com.springboot.weather_monitoring.domains.entities.WeatherData;
import com.springboot.weather_monitoring.mappers.WeatherMapper;
import com.springboot.weather_monitoring.repositories.LocationRepository;
import com.springboot.weather_monitoring.services.WeatherDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(path = "/history")
    public ResponseEntity<List<WeatherData>> getWeatherHistory(@RequestParam String cityName){

        List<WeatherData> history = weatherDataService.getHistory(cityName);
        return ResponseEntity.ok(history);
    }
}
