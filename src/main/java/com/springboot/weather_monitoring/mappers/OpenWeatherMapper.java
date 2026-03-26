package com.springboot.weather_monitoring.mappers;

import com.springboot.weather_monitoring.domains.dtos.OpenWeatherResponse;
import com.springboot.weather_monitoring.domains.entities.WeatherData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OpenWeatherMapper {

    @Mapping(source = "main.temp", target = "temperature")
    @Mapping(source = "main.humidity", target = "humidity")
    @Mapping(source = "main.pressure", target = "pressure")
    @Mapping(source = "wind.speed", target = "windSpeed")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "location", ignore = true)
    @Mapping(target = "recordedAt", ignore = true)
    WeatherData toWeatherData(OpenWeatherResponse response);

}
