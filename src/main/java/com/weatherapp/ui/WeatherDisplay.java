package com.weatherapp.ui;

import com.weatherapp.dto.WeatherDTO;

public class WeatherDisplay {

    public void show(WeatherDTO weather) {
        System.out.println("\n=========================");
        System.out.println("   WEATHER APP ☀️");
        System.out.println("=========================");
        System.out.println("🌎 Cidade: " + weather.getCidade());
        System.out.println("🌡️ Temperatura: " + weather.getTemperatura() + "°C");
        System.out.println("=========================\n");
    }

    public void showError(String message) {
        System.out.println("❌ Erro: " + message);
    }
}