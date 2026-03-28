package com.weatherapp.dto;

public class WeatherDTO {

    private String cidade;
    private double temperatura;

    public WeatherDTO(String cidade, double temperatura) {
        this.cidade = cidade;
        this.temperatura = temperatura;
    }

    public String getCidade() {
        return cidade;
    }

    public double getTemperatura() {
        return temperatura;
    }
}