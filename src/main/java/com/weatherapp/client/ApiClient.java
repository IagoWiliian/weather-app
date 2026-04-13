package com.weatherapp.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.weatherapp.util.HttpUtil;

public class ApiClient {

    public JsonObject getLocationData(String city) throws Exception {
        String url = "https://geocoding-api.open-meteo.com/v1/search?name="
                + city.replace(" ", "%20");

        String response = getResponse(url);
        return JsonParser.parseString(response).getAsJsonObject();
    }

   public JsonObject getWeatherData(double lat, double lon) throws Exception {

    String url = String.format(
            "https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&current=temperature_2m,relative_humidity_2m,wind_speed_10m,precipitation",
            lat, lon
    );

    String response = HttpUtil.get(url);

    return JsonParser.parseString(response).getAsJsonObject();
}

    private String getResponse(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));

        StringBuilder result = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            result.append(line);
        }

        reader.close();
        return result.toString();
    }
}