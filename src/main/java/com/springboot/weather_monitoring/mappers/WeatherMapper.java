package com.springboot.weather_monitoring.mappers;

import com.springboot.weather_monitoring.domains.dtos.WeatherResponseDto;
import com.springboot.weather_monitoring.domains.entities.WeatherData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WeatherMapper {

    @Mapping(source = "location.cityName", target = "cityName")
    WeatherResponseDto toDto(WeatherData weatherData);

}
