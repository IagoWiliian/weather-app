package com.weatherapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.weatherapp.client.ApiClient;
import com.weatherapp.controller.WeatherController;
import com.weatherapp.service.WeatherService;
import com.weatherapp.ui.WeatherDisplay;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ApiClient apiClient = new ApiClient();
        WeatherService service = new WeatherService(apiClient);
        WeatherDisplay display = new WeatherDisplay();

        WeatherController controller = new WeatherController(service, display);

       System.out.print("Digite as cidades separadas por vírgula: ");
String input = scanner.nextLine();

String[] cidadesArray = input.split(",");

List<String> cidades = new ArrayList<>();

for (String cidade : cidadesArray) {
    cidades.add(cidade.trim());
}

controller.buscarClimaEmLote(cidades);
    }
}