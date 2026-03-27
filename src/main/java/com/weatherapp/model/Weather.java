package com.weatherapp.model;

public class Weather {
    private final String description;
    private final double temperature;

    public Weather(String description, double temperature) {
        this.description = description;
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public double getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return String.format("%s, %.1f°C", description, temperature);
    }
}
