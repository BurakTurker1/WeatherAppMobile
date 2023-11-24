package com.burakturker.weatherapp.model;

import com.google.gson.annotations.SerializedName;

public class WeatherModel {

    @SerializedName("current")
    private CurrentWeather currentWeather;

    @SerializedName("location")
    private Location location;

    public String getWeatherCode() {
        if (currentWeather != null && currentWeather.getCondition() != null) {
            return currentWeather.getCondition().getCode();
        }
        return null;
    }

    public String getWeatherDescription() {
        if (currentWeather != null && currentWeather.getCondition() != null) {
            return currentWeather.getCondition().getText();
        }
        return null;
    }

    public String getTemperatureCelsius() {
        if (currentWeather != null) {
            return currentWeather.getTemperatureCelsius();
        }
        return null;
    }

    public String getCityName() {
        if (location != null) {
            return location.getName();
        }
        return null;
    }

    public static class CurrentWeather {
        @SerializedName("last_updated_epoch")
        private long lastUpdatedEpoch;

        @SerializedName("temp_c")
        private String temperatureCelsius;

        @SerializedName("condition")
        private WeatherCondition condition;

        public String getTemperatureCelsius() {
            return temperatureCelsius;
        }

        public WeatherCondition getCondition() {
            return condition;
        }
    }

    public static class Location {
        @SerializedName("name")
        private String name;

        public String getName() {
            return name;
        }
    }

    public static class WeatherCondition {
        @SerializedName("text")
        private String text;

        @SerializedName("code")
        private String code;

        public String getText() {
            return text;
        }

        public String getCode() {
            return code;
        }
    }
}