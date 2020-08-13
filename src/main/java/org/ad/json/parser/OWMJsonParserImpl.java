package org.ad.json.parser;

import org.ad.forecast.WeatherForecastElement;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class OWMJsonParserImpl implements JsonParser {

    private static final Double KELVIN_CONSTANT = 273.15;

    private static final JSONParser jsonParser = new JSONParser();

    public WeatherForecastElement parse (String jsonStringObject) throws ParseException {
        Object object = jsonParser.parse(jsonStringObject);
        JSONObject jsonObject = (JSONObject)object;
        return parse(jsonObject);
    }

    public WeatherForecastElement parse (JSONObject jsonObject) {
        JSONObject mainObj = (JSONObject)jsonObject.get("main");
        Double currentTemperature = (Double)mainObj.get("temp") - KELVIN_CONSTANT;
        Double minTemperature = (Double)mainObj.get("temp_min") - KELVIN_CONSTANT;
        Double maxTemperature = (Double)mainObj.get("temp_max") - KELVIN_CONSTANT;
        Double feelsLikeTemperature = (Double)mainObj.get("feels_like") - KELVIN_CONSTANT;

        return new WeatherForecastElement(minTemperature, maxTemperature, currentTemperature,
                feelsLikeTemperature);
    }

}
