package com.weatherapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.weatherapp.client.ApiClient;
import com.weatherapp.dto.WeatherDTO;
import com.weatherapp.cache.CacheEntry;

public class WeatherService {

    private ApiClient apiClient;

    public WeatherService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

public WeatherDTO getWeather(String city) throws Exception {

    validateCity(city);

    long now = System.currentTimeMillis();

    // 🔍 verifica cache
    if (cache.containsKey(city)) {
        CacheEntry entry = cache.get(city);

        if ((now - entry.getTimestamp()) < CACHE_TTL) {
            System.out.println("⚡ Usando cache para: " + city);
            return entry.getData();
        }
    }

    // 🌐 chamada real da API
    JsonObject location = getLocation(city);

    double lat = location.get("latitude").getAsDouble();
    double lon = location.get("longitude").getAsDouble();

    double temperature = getTemperature(lat, lon);

    WeatherDTO dto = new WeatherDTO(city, temperature);

    // 💾 salva no cache
    cache.put(city, new CacheEntry(dto, now));

    return dto;
}

    private void validateCity(String city) {
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("Cidade não pode ser vazia");
        }
    }
private Map<String, CacheEntry> cache = new HashMap<>();
private static final long CACHE_TTL = 3600000; // 1 hora (em ms)

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