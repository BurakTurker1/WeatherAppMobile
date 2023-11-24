package com.burakturker.weatherapp.adapter;

import com.burakturker.weatherapp.service.WeatherApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApiClient {
    private static final String BASE_URL = "http://api.weatherapi.com/v1/";
    private static final String API_KEY = "4a96820d86d449fd821215458231611";

    private Retrofit retrofit;

    public WeatherApiClient() {
        // Logging interceptor oluşturuluyor
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // OkHttpClient oluşturuluyor ve interceptor ekleniyor
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        // Retrofit nesnesi oluşturuluyor
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public WeatherApi getWeatherApi() {
        return retrofit.create(WeatherApi.class);
    }

    public String getApiKey() {
        return API_KEY;
    }
}
