package com.burakturker.weatherapp.service;

import com.burakturker.weatherapp.model.WeatherModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherApi {
    //Current Weather: Get current weather data.
    //Historical Weather: Get historical weather data.
    //Historical Time-Series: Get historical time-series weather data.
    //Weather Forecast: Get weather forecast for up to 14 days.
    //Location Lookup: Look up one or multiple locations.
    //bb2c05458459bcb5e55df7ca769c7074
    // base url http://api.weatherstack.com/


    //http://api.weatherstack.com/current
    //    ? access_key = YOUR_ACCESS_KEY
    //    & query = New York
    @GET("current? access_key =bb2c05458459bcb5e55df7ca769c7074")
    Call<List<WeatherModel>> getData();
}
