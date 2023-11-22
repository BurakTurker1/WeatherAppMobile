// WeatherApi.java

package com.burakturker.weatherapp.service;

import com.burakturker.weatherapp.model.WeatherModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    // Örnek istek:
    // http://api.weatherapi.com/v1/current.json?key=API_KEY&q=CityName
    @GET("current.json?key=4a96820d86d449fd821215458231611")
    Call<WeatherModel> getData(@Query("q") String cityName);
}

