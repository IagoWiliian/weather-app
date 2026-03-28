package com.weatherapp.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.weatherapp.client.ApiClient;
import com.weatherapp.dto.WeatherDTO;

public class WeatherService {

    private ApiClient apiClient = new ApiClient();

    public WeatherDTO getWeather(String city) throws Exception {

        JsonObject geoJson = apiClient.getLocationData(city);
        JsonArray results = geoJson.getAsJsonArray("results");

        if (results == null || results.size() == 0) {
            throw new Exception("Cidade não encontrada");
        }

        JsonObject location = results.get(0).getAsJsonObject();
        double lat = location.get("latitude").getAsDouble();
        double lon = location.get("longitude").getAsDouble();

        JsonObject weatherJson = apiClient.getWeatherData(lat, lon);
        JsonObject current = weatherJson.getAsJsonObject("current_weather");

        double temp = current.get("temperature").getAsDouble();

        return new WeatherDTO(city, temp);
    }
}