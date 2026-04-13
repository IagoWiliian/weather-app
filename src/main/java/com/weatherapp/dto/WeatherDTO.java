package com.weatherapp.dto;

public class WeatherDTO {

    private String city;
    private double temperature;
    private double humidity;
    private double windSpeed;
    private double precipitation;

    public WeatherDTO(String city, double temperature, double humidity, double windSpeed, double precipitation) {
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.precipitation = precipitation;
    }

    public String getCity() { return city; }
    public double getTemperature() { return temperature; }
    public double getHumidity() { return humidity; }
    public double getWindSpeed() { return windSpeed; }
    public double getPrecipitation() { return precipitation; }
}