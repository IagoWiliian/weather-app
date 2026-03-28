package com.weatherapp;

import java.util.Scanner;
import com.weatherapp.controller.WeatherController;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WeatherController controller = new WeatherController();

        System.out.print("Digite a cidade: ");
        String cidade = scanner.nextLine();

        controller.buscarClima(cidade);

        scanner.close();
    }
}