package com.springboot.weather_monitoring.domains.dtos;

import lombok.Data;

import java.util.List;

@Data
public class OpenWeatherResponse {

    private Main main;
    private Wind wind;
    private List<Weather> weather;

    @Data
    public static class Main {
        private double temp;
        private double humidity;
        private double pressure;
    }

    @Data
    public static class Wind {
        private double speed;
    }

    @Data
    public static class Weather {
        private String description;
    }
}

