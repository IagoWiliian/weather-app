package com.weatherapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Digite o nome da cidade: ");
            String cidade = scanner.nextLine();

            // 1. Buscar latitude e longitude
            String geoUrl = "https://geocoding-api.open-meteo.com/v1/search?name="
                    + cidade.replace(" ", "%20");

            String geoResponse = getResponse(geoUrl);

            JsonObject geoJson = JsonParser.parseString(geoResponse).getAsJsonObject();
            JsonArray results = geoJson.getAsJsonArray("results");

            if (results == null || results.size() == 0) {
                System.out.println("Cidade não encontrada!");
                return;
            }

            JsonObject location = results.get(0).getAsJsonObject();
            double latitude = location.get("latitude").getAsDouble();
            double longitude = location.get("longitude").getAsDouble();

            // 2. Buscar clima
            String weatherUrl = "https://api.open-meteo.com/v1/forecast?latitude="
                    + latitude + "&longitude=" + longitude + "&current_weather=true";

            String weatherResponse = getResponse(weatherUrl);

            JsonObject weatherJson = JsonParser.parseString(weatherResponse).getAsJsonObject();
            JsonObject currentWeather = weatherJson.getAsJsonObject("current_weather");

            double temperature = currentWeather.get("temperature").getAsDouble();

            // 3. Exibir resultado
            System.out.println("\n🌎 Cidade: " + cidade);
            System.out.println("🌡️ Temperatura atual: " + temperature + "°C");

        } catch (Exception e) {
            System.out.println("Erro ao buscar dados: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Método para fazer requisição HTTP
    private static String getResponse(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));

        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        return response.toString();
    }
}