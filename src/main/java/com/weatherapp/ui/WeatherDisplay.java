package com.weatherapp.ui;

import com.weatherapp.dto.WeatherDTO;

public class WeatherDisplay {

    public void show(WeatherDTO dto) {

        printHeader();

        System.out.println("🌎 Cidade: " + dto.getCity());
        System.out.println("🌡️ Temperatura: " + dto.getTemperature() + "°C");
        System.out.println("💧 Umidade: " + dto.getHumidity() + "%");
        System.out.println("🌬️ Vento: " + dto.getWindSpeed() + " km/h");
        System.out.println("🌧️ Precipitação: " + dto.getPrecipitation() + " mm");

        System.out.println("-------------------------");

        System.out.println("📊 Condição: " + getCondition(dto));
        System.out.println("💡 Sugestão: " + getSuggestion(dto));

        printFooter();
    }

    public void showError(String message) {
        printHeader();
        System.out.println("❌ Erro: " + message);
        printFooter();
    }

    // =========================
    // PADRÃO DE UI
    // =========================

    private void printHeader() {
        System.out.println("\n=========================");
        System.out.println("   WEATHER APP ☀️");
        System.out.println("=========================");
    }

    private void printFooter() {
        System.out.println("=========================\n");
    }

    // =========================
    // LÓGICA DE CONDIÇÃO
    // =========================

    private String getCondition(WeatherDTO dto) {

        double temp = dto.getTemperature();
        double rain = dto.getPrecipitation();

        if (rain > 5) {
            return "🌧️ Chuvoso";
        }

        if (temp >= 30) {
            return "🔥 Muito quente";
        }

        if (temp >= 20) {
            return "🌤️ Agradável";
        }

        return "❄️ Frio";
    }

    // =========================
    // SUGESTÃO INTELIGENTE
    // =========================

    private String getSuggestion(WeatherDTO dto) {

        double temp = dto.getTemperature();
        double rain = dto.getPrecipitation();

        if (rain > 5) {
            return "Leve um guarda-chuva ☔";
        }

        if (temp >= 30) {
            return "Hidrate-se bem 💧";
        }

        if (temp >= 20) {
            return "Ótimo dia para sair 🌤️";
        }

        return "Use agasalho 🧥";
    }
}