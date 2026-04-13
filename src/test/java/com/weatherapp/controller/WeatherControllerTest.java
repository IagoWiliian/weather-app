package com.weatherapp.controller;

import com.weatherapp.dto.WeatherDTO;
import com.weatherapp.service.WeatherService;
import com.weatherapp.ui.WeatherDisplay;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

class WeatherControllerTest {

    @Test
    void naoDeveQuebrarAoBuscarClima() throws Exception {

        // mocks
        WeatherService mockService = mock(WeatherService.class);
        WeatherDisplay mockDisplay = mock(WeatherDisplay.class);

        // DTO simulado (ajuste conforme seu construtor real)
        WeatherDTO dto = new WeatherDTO(
            "Belo Horizonte",
            25.0,
            60.0,
            10.0,
            0.0
        );

        // comportamento do service mockado
        when(mockService.getWeather("Belo Horizonte")).thenReturn(dto);

        // controller com dependências mockadas
        WeatherController controller = new WeatherController(mockService, mockDisplay);

        // execução
        controller.buscarClima("Belo Horizonte");

        // verificação (não depende do objeto exato)
        verify(mockDisplay).show(any(WeatherDTO.class));
    }
}