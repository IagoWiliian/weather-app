package com.weatherapp.controller;

import com.weatherapp.dto.WeatherDTO;
import com.weatherapp.service.WeatherService;
import com.weatherapp.ui.WeatherDisplay;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class WeatherControllerTest {

    @Test
    void naoDeveQuebrarAoBuscarClima() throws Exception {

        WeatherService mockService = mock(WeatherService.class);
        WeatherDisplay mockDisplay = mock(WeatherDisplay.class);

        WeatherDTO dto = new WeatherDTO("Belo Horizonte", 25.0);

        when(mockService.getWeather("Belo Horizonte")).thenReturn(dto);

        WeatherController controller = new WeatherController(mockService, mockDisplay);

        controller.buscarClima("Belo Horizonte");

        verify(mockDisplay).show(dto);
    }
}