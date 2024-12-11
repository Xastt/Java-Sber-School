package ru.xast.sbertasks.task8.WeatherForecast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

    private Current current;

    public Current getCurrent() {
        return current;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Current{
        private double tempC;
        private Condition condition;

        @JsonProperty("temp_c")
        public double getTempC() {
            return tempC;
        }

        @JsonProperty("condition")
        public Condition getCondition() {
            return condition;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Condition{
        private String text;

        @JsonProperty("text")
        public String getText() {
            return text;
        }
    }
}
