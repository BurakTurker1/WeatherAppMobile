package com.burakturker.weatherapp.model;

import com.google.gson.annotations.SerializedName;

public class WeatherModel {

    @SerializedName("temperature")
    String Derece;

    @SerializedName("wind_speed")
    String ruzgarHiz;

    @SerializedName("wind_degree")
    String ruzgarDerece;

    @SerializedName("pressure")
    String basinc;

    @SerializedName("humidity")
    String nem;

    @SerializedName("weather_descriptions")
    String havaDurumAciklama;

    @SerializedName("name")
    String isim;

    @SerializedName("weather_icons")
    String havaDurumIcon;
}
