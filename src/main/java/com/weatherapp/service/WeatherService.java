package com.weatherapp.service;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.weatherapp.client.ApiClient;
import com.weatherapp.dto.WeatherDTO;

public class WeatherService {

    private ApiClient apiClient;

    public WeatherService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public WeatherDTO getWeather(String city) throws Exception {

        validateCity(city);

        JsonObject location = getLocation(city);

        double lat = location.get("latitude").getAsDouble();
        double lon = location.get("longitude").getAsDouble();

        double temperature = getTemperature(lat, lon);

        return new WeatherDTO(city, temperature);
    }

    private void validateCity(String city) {
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("Cidade não pode ser vazia");
        }
    }


    public List<WeatherDTO> getWeather(List<String> cities) {

    List<WeatherDTO> results = new ArrayList<>();

    for (String city : cities) {
        try {
            WeatherDTO weather = getWeather(city);
            results.add(weather);
        } catch (Exception e) {
            System.out.println("Erro na cidade: " + city);
        }
    }

    return results;
}
    private JsonObject getLocation(String city) throws Exception {

        JsonObject geoJson = apiClient.getLocationData(city);
        JsonArray results = geoJson.getAsJsonArray("results");

        if (results == null || results.size() == 0) {
            throw new Exception("Cidade não encontrada");
        }

        return results.get(0).getAsJsonObject();
    }

    private double getTemperature(double lat, double lon) throws Exception {

        JsonObject weatherJson = apiClient.getWeatherData(lat, lon);

        // 🔥 correção aqui
        if (!weatherJson.has("current")) {
            throw new Exception("Dados de clima indisponíveis");
        }

        JsonObject current = weatherJson.getAsJsonObject("current");

        if (!current.has("temperature_2m")) {
            throw new Exception("Temperatura não encontrada");
        }

        return current.get("temperature_2m").getAsDouble();
    }
}