package com.weatherapp.service;

import com.weatherapp.client.ApiClient;
import com.weatherapp.model.Location;
import com.weatherapp.model.WeatherResponse;
import com.weatherapp.model.Weather;
import com.weatherapp.util.JsonParser;
import com.weatherapp.util.Constants;

import java.util.Map;

public class WeatherService {
    private final ApiClient api = new ApiClient();

    public WeatherResponse getWeatherByLocation(Location location) throws Exception {
        String city = location.getCity();
        String url = Constants.buildUrlForCity(city);
        String json = api.fetch(url);

        Map<String, Object> parsed = JsonParser.parseToMap(json);
        // minimal parsing - adapt conforme a API
        Map<String, Object> main = (Map<String, Object>) parsed.get("main");
        Object tempObj = main != null ? main.get("temp") : null;
        double temp = tempObj instanceof Number ? ((Number) tempObj).doubleValue() : 0.0;

        String description = "";
        Object weatherArr = parsed.get("weather");
        if (weatherArr instanceof java.util.List) {
            java.util.List list = (java.util.List) weatherArr;
            if (!list.isEmpty() && list.get(0) instanceof Map) {
                Map w = (Map) list.get(0);
                Object desc = w.get("description");
                description = desc != null ? desc.toString() : "";
            }
        }

        Weather w = new Weather(description, temp);
        WeatherResponse wd = new WeatherResponse(location, w, System.currentTimeMillis());
        return wd;
    }
}
