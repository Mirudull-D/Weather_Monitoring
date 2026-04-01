package com.springboot.weather_monitoring.services.impl;

import com.springboot.weather_monitoring.domains.dtos.DailySummaryDto;
import com.springboot.weather_monitoring.domains.dtos.OpenWeatherResponse;
import com.springboot.weather_monitoring.domains.dtos.WeatherRequestDto;
import com.springboot.weather_monitoring.domains.entities.Location;
import com.springboot.weather_monitoring.domains.entities.WeatherData;
import com.springboot.weather_monitoring.mappers.OpenWeatherMapper;

import com.springboot.weather_monitoring.repositories.DailySummaryRepository;
import com.springboot.weather_monitoring.repositories.LocationRepository;
import com.springboot.weather_monitoring.repositories.WeatherDataRepository;
import com.springboot.weather_monitoring.services.WeatherDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherDataServiceImpl implements WeatherDataService {

    public final WeatherDataRepository weatherDataRepository;
    public final RestTemplate restTemplate;
    private final OpenWeatherMapper openWeatherMapper;
    private final LocationRepository locationRepository;
    private final DailySummaryRepository dailySummaryRepository;


    @Value("${weather.api.key}")
    public String apiKey;

    @Override
    public WeatherData fetchAndSaveWeatherData(WeatherRequestDto weatherRequestDto) {

        Location location = locationRepository.findByCityName(weatherRequestDto.getCityName())
                .orElseGet(()->
                        locationRepository.save(
                                Location.builder()
                                        .cityName(weatherRequestDto.getCityName())
                                        .build()
                        )
                );
        String city = weatherRequestDto.getCityName();

        String url = "https://api.openweathermap.org/data/2.5/weather?q="
                + city + "&appid=" + apiKey + "&units=metric";

        OpenWeatherResponse openWeatherResponse = restTemplate
                .getForObject(url, OpenWeatherResponse.class);

        WeatherData weatherData = openWeatherMapper.toWeatherData(openWeatherResponse);

        weatherData.setLocation(location);
        weatherData.setRecordedAt(LocalDateTime.now());

        return weatherDataRepository.save(weatherData);

    }

    @Override
    public List<WeatherData> getHistory(String cityName) {
        return weatherDataRepository.findByLocationCityNameOrderByRecordedAtDesc(cityName);
    }

    @Override
    public List<DailySummaryDto> getDailySummary(String city) {
        List<Object[]> results = dailySummaryRepository.getDailySummaryRaw(city);

        return results.stream().map(result -> {
            return new DailySummaryDto(
                    LocalDate.parse(result[0].toString()),
                    ((Number) result[1]).doubleValue(),
                    ((Number) result[2]).doubleValue(),
                    ((Number) result[3]).doubleValue()
            );
        }).collect(Collectors.toList());
    }
}
