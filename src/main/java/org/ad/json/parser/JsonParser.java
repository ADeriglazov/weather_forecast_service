package org.ad.json.parser;

import org.ad.forecast.WeatherForecastElement;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


public interface JsonParser {

    WeatherForecastElement parse (String jsonStringObject) throws ParseException;

    WeatherForecastElement parse (JSONObject jsonObject);

}
