package com.weatherapp;

import java.util.Scanner;

import com.weatherapp.client.ApiClient;
import com.weatherapp.controller.WeatherController;
import com.weatherapp.service.WeatherService;
import com.weatherapp.ui.WeatherDisplay;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // 🔥 Montando dependências (manual)
        ApiClient apiClient = new ApiClient();
        WeatherService service = new WeatherService(apiClient);
        WeatherDisplay display = new WeatherDisplay();
        WeatherController controller = new WeatherController(service, display);

        System.out.print("Digite a cidade: ");
        String cidade = scanner.nextLine();

        controller.buscarClima(cidade);

        scanner.close();
    }
}