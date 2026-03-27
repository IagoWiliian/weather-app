package com.weatherapp.controller;

import com.weatherapp.model.Location;
import com.weatherapp.model.WeatherResponse;
import com.weatherapp.service.WeatherService;
import com.weatherapp.ui.WeatherDisplay;

import java.util.Scanner;

public class WeatherController {
    private final WeatherService service = new WeatherService();
    private final WeatherDisplay display = new WeatherDisplay();

    public void start() {
        System.out.println("Bem-vindo ao WEATHER.app");
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o nome da cidade: ");
        String city = sc.nextLine().trim();

        try {
            Location loc = new Location(city);
            WeatherResponse data = service.getWeatherByLocation(loc);
            display.show(data);
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
