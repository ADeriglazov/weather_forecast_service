package org.ad.request.handler;

import org.ad.forecast.WeatherForecastElement;
import org.ad.forecast.builder.ForecastBuilder;
import org.ad.json.parser.JsonParser;
import org.ad.json.parser.OWMJsonParserImpl;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class OWMWeatherForecastRequestHandlerImpl implements WeatherForecastRequestHandler {

    private static final JsonParser jsonParser = new OWMJsonParserImpl();
    private static final RequestHandler requestHandler = new HttpRequestHandlerImpl();

    private static final String REQUEST_FORMAT = "http://api.openweathermap.org/data/" +
            "2.5/weather?q=%s&appid=7b9accd2885c4498a9d73df5e027a4e4";
    private static final String MINSK_CITY_NAME = "Minsk";

    public String getResponse(String requestStr) throws IOException {
        WeatherForecastElement forecastElement = getResponseElement(requestStr);
        return ForecastBuilder.build(forecastElement);
    }

    public WeatherForecastElement getResponseElement(String requestStr) throws IOException {
        String mainRequest = String.format(REQUEST_FORMAT, requestStr);
        String comparativeRequest = String.format(REQUEST_FORMAT, MINSK_CITY_NAME);

        String mainResponse = requestHandler.getResponse(mainRequest);
        String comparativeResponse = requestHandler.getResponse(comparativeRequest);

        WeatherForecastElement main = null;
        WeatherForecastElement comparative = null;

        try {
            main = jsonParser.parse(mainResponse);
            main.setCity(requestStr);
        } catch(ParseException e) {
            throw new IOException(String.format("Error parsing json: %s\n", mainResponse), e);
        }

        try {
            comparative = jsonParser.parse(comparativeResponse);
            comparative.setCity(MINSK_CITY_NAME);
        } catch(ParseException e) {
            throw new IOException(String.format("Error parsing json: %s\n", comparativeResponse), e);
        }

        main.setComparative(comparative);
        return main;
    }

}
