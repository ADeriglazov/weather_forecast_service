package org.ad.forecast;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class WeatherForecastElement {

    @Getter
    @Setter
    @NonFinal
    private String city;

    @Getter
    @NonNull
    private Double minTemperature;

    @Getter
    @NonNull
    private Double maxTemperature;

    @Getter
    @NonNull
    private Double currentTemperature;

    @Getter
    @NonNull
    private Double feelsLikeTemperature;

    @Getter
    @Setter
    @NonFinal
    WeatherForecastElement comparative;

}
