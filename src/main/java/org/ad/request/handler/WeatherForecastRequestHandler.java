package org.ad.request.handler;

import org.ad.forecast.WeatherForecastElement;

import java.io.IOException;

public interface WeatherForecastRequestHandler extends RequestHandler {

    WeatherForecastElement getResponseElement(String requestStr) throws IOException;

}
