package com.springboot.weather_monitoring.services;

import com.springboot.weather_monitoring.domains.dtos.DailySummaryDto;
import com.springboot.weather_monitoring.domains.dtos.WeatherRequestDto;
import com.springboot.weather_monitoring.domains.entities.WeatherData;

import java.util.List;

public interface WeatherDataService {

    WeatherData fetchAndSaveWeatherData(WeatherRequestDto weatherRequestDto);
     List<WeatherData> getHistory(String cityName);
     List<DailySummaryDto> getDailySummary(String city);


}
