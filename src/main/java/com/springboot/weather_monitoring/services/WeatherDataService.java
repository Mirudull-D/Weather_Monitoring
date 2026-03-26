package com.springboot.weather_monitoring.services;

import com.springboot.weather_monitoring.domains.dtos.WeatherRequestDto;
import com.springboot.weather_monitoring.domains.entities.WeatherData;

public interface WeatherDataService {

    WeatherData fetchAndSaveWeatherData(WeatherRequestDto weatherRequestDto);

}
