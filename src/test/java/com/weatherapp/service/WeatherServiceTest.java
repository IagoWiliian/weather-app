package com.weatherapp.service;

import com.weatherapp.model.Location;
import com.weatherapp.model.WeatherResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherServiceTest {
    @Test
    public void testGetWeatherByLocation_nullCity_throws() {
        WeatherService service = new WeatherService();
        assertThrows(Exception.class, () -> service.getWeatherByLocation(new Location("")));
    }
}
