package com.burakturker.weatherapp.service;

import com.burakturker.weatherapp.model.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("current.json")
    Call<WeatherModel> getWeather(
            @Query("q") String cityName,
            @Query("key") String apiKey
    );
}
