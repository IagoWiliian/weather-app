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

    // 💾 Cache em memória
    private Map<String, CacheEntry> cache = new HashMap<>();
    private static final long CACHE_TTL = 3600000; // 1 hora

    public WeatherService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    // 🔥 MÉTODO PRINCIPAL (COM CACHE + DADOS COMPLETOS)
    public WeatherDTO getWeather(String city) throws Exception {

        validateCity(city);

        long now = System.currentTimeMillis();

        // 🔍 Verifica cache
        if (cache.containsKey(city)) {
            CacheEntry entry = cache.get(city);

            if ((now - entry.getTimestamp()) < CACHE_TTL) {
                System.out.println("⚡ Usando cache para: " + city);
                return entry.getData();
            }
        }

        // 🌍 Busca localização
        JsonObject location = getLocation(city);

        double lat = location.get("latitude").getAsDouble();
        double lon = location.get("longitude").getAsDouble();

        // 🌐 Busca clima completo
        JsonObject weatherJson = apiClient.getWeatherData(lat, lon);

        if (!weatherJson.has("current")) {
            throw new Exception("Dados de clima indisponíveis");
        }

        JsonObject current = weatherJson.getAsJsonObject("current");

        // 🌡️ Extrai dados
        double temperature = current.get("temperature_2m").getAsDouble();
        double humidity = current.get("relative_humidity_2m").getAsDouble();
        double windSpeed = current.get("wind_speed_10m").getAsDouble();
        double precipitation = current.get("precipitation").getAsDouble();

        // 📦 Cria DTO
        WeatherDTO dto = new WeatherDTO(
                city,
                temperature,
                humidity,
                windSpeed,
                precipitation
        );

        // 💾 Salva no cache
        cache.put(city, new CacheEntry(dto, now));

        return dto;
    }

    // 📦 SUPORTE A MÚLTIPLAS CIDADES
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

    // 🔒 Validação
    private void validateCity(String city) {
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("Cidade não pode ser vazia");
        }
    }

    // 🌍 Busca coordenadas
    private JsonObject getLocation(String city) throws Exception {

        JsonObject geoJson = apiClient.getLocationData(city);
        JsonArray results = geoJson.getAsJsonArray("results");

        if (results == null || results.size() == 0) {
            throw new Exception("Cidade não encontrada");
        }

        return results.get(0).getAsJsonObject();
    }
    public String getWeatherSuggestion(double temp, double precipitation) {

    if (precipitation > 5) {
        return "🌧️ Leve um guarda-chuva";
    }

    if (temp >= 30) {
        return "🔥 Dia muito quente, hidrate-se bem";
    }

    if (temp >= 20) {
        return "🌤️ Dia agradável para sair";
    }

    return "❄️ Dia frio, vista-se bem agasalhado";
}
}