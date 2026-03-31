package com.weatherapp.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.weatherapp.client.ApiClient;
import com.weatherapp.dto.WeatherDTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeatherServiceTest {

    @Test
    void deveRetornarTemperaturaComMock() throws Exception {

        ApiClient mockApi = mock(ApiClient.class);

        JsonObject geoJson = new JsonObject();
        JsonArray results = new JsonArray();
        JsonObject location = new JsonObject();

        location.addProperty("latitude", -19.92);
        location.addProperty("longitude", -43.94);
        results.add(location);
        geoJson.add("results", results);

        JsonObject weatherJson = new JsonObject();
        JsonObject current = new JsonObject();
        current.addProperty("temperature", 25.0);
        weatherJson.add("current_weather", current);

        when(mockApi.getLocationData("Belo Horizonte")).thenReturn(geoJson);
        when(mockApi.getWeatherData(anyDouble(), anyDouble())).thenReturn(weatherJson);

        WeatherService service = new WeatherService(mockApi);

        WeatherDTO result = service.getWeather("Belo Horizonte");

        assertNotNull(result);
        assertEquals("Belo Horizonte", result.getCidade());
        assertEquals(25.0, result.getTemperatura());

        verify(mockApi).getLocationData("Belo Horizonte");
        verify(mockApi).getWeatherData(anyDouble(), anyDouble());
    }
}