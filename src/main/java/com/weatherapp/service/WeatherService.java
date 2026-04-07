package com.weatherapp.service;

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

    // 🔹 Validação de entrada
    if (city == null || city.trim().isEmpty()) {
        throw new IllegalArgumentException("Cidade não pode ser vazia");
    }

    // 🔹 Busca localização
    JsonObject geoJson = apiClient.getLocationData(city);
    JsonArray results = geoJson.getAsJsonArray("results");

    if (results == null || results.size() == 0) {
        throw new Exception("Cidade não encontrada");
    }

    JsonObject location = results.get(0).getAsJsonObject();

    double lat = location.get("latitude").getAsDouble();
    double lon = location.get("longitude").getAsDouble();

    // 🔹 Busca clima
    JsonObject weatherJson = apiClient.getWeatherData(lat, lon);

    if (weatherJson.get("current_weather") == null) {
        throw new Exception("Dados de clima indisponíveis");
    }

    JsonObject current = weatherJson.getAsJsonObject("current_weather");

    if (current.get("temperature") == null) {
        throw new Exception("Temperatura não encontrada");
    }

    double temp = current.get("temperature").getAsDouble();

    return new WeatherDTO(city, temp);
}
}