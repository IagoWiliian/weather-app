package com.weatherapp.cache;

import com.weatherapp.dto.WeatherDTO;

public class CacheEntry {

    private WeatherDTO data;
    private long timestamp;

    public CacheEntry(WeatherDTO data, long timestamp) {
        this.data = data;
        this.timestamp = timestamp;
    }

    public WeatherDTO getData() {
        return data;
    }

    public long getTimestamp() {
        return timestamp;
    }
}