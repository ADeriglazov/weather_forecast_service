package org.ad.forecast.builder;

import org.ad.forecast.WeatherForecastElement;

public class ForecastBuilder {

    private static final String MINSK_FORECAST_FORMAT = "Сегодня в Минске от %.0f до %.0f °C. " +
            "Текущая температура %.0f°C (ощущается как %.0f°C).\n " +
            "Берегите себя и своих близких!";

    private static final String COMMON_FORECAST_FORMAT = "Сегодня в городе %s от %.0f до %.0f °C. " +
            "Текущая температура %.0f°C, но по ощущениям %.0f°C.\n " +
            "Для сравнения в столице Республики Беларусь %.0f°C.\n " +
            "Берегите себя и своих близких!";

    public static String build(WeatherForecastElement element) {
        String result;
        if(element.getCity().toLowerCase().equals("minsk") ||
                element.getCity().toLowerCase().equals("минск")) {
            result = String.format(MINSK_FORECAST_FORMAT, element.getMinTemperature(),
                    element.getMaxTemperature(), element.getCurrentTemperature(),
                    element.getFeelsLikeTemperature());
        } else {
            result = String.format(COMMON_FORECAST_FORMAT,element.getCity(),
                    element.getMinTemperature(), element.getMaxTemperature(),
                    element.getCurrentTemperature(), element.getFeelsLikeTemperature(),
                    element.getComparative().getCurrentTemperature());
        }
        return result;
    }

}
