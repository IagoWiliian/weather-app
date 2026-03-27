package com.weatherapp.ui;

import com.weatherapp.model.WeatherResponse;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherDisplay {
    public void show(WeatherResponse data) {
        if (data == null) {
            System.out.println("Nenhum dado para exibir.");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Cidade: " + data.getLocation().getCity());
        System.out.println("Clima: " + data.getWeather().toString());
        System.out.println("Atualizado em: " + sdf.format(new Date(data.getTimestamp())));
    }
}
