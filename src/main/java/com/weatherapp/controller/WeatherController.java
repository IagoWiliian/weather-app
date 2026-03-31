package com.weatherapp.controller;

import com.weatherapp.dto.WeatherDTO;
import com.weatherapp.service.WeatherService;
import com.weatherapp.ui.WeatherDisplay;

public class WeatherController {

    private WeatherService service;
    private WeatherDisplay display;

    public WeatherController(WeatherService service, WeatherDisplay display) {
        this.service = service;
        this.display = display;
    }

    public void buscarClima(String cidade) {
        try {
            WeatherDTO weather = service.getWeather(cidade);
            display.show(weather);

        } catch (Exception e) {
            display.showError(e.getMessage());
        }
    }
}