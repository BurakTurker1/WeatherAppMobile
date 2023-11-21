package com.burakturker.weatherapp.model;

import com.google.gson.annotations.SerializedName;

public class WeatherModel {

    @SerializedName("temperature")
    public String Derece;

    @SerializedName("wind_speed")
    public String ruzgarHiz;

    @SerializedName("wind_degree")
    public   String ruzgarDerece;

    @SerializedName("pressure")
     public String basinc;

    @SerializedName("humidity")
    public String nem;

    @SerializedName("weather_descriptions")
    public String havaDurumAciklama;

    @SerializedName("name")
    public String isim;

    @SerializedName("weather_icons")
    public String havaDurumIcon;
}
