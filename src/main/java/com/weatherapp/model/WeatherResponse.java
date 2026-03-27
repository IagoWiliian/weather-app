package com.weatherapp.model;

public class WeatherResponse {
    private final Location location;
    private final Weather weather;
    private final long timestamp;

    public WeatherResponse(Location location, Weather weather, long timestamp) {
        this.location = location;
        this.weather = weather;
        this.timestamp = timestamp;
    }

    public Location getLocation() {
        return location;
    }

    public Weather getWeather() {
        return weather;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
