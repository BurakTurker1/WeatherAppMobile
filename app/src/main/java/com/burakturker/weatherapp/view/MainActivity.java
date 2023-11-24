package com.burakturker.weatherapp.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.burakturker.weatherapp.R;
import com.burakturker.weatherapp.adapter.WeatherApiClient;
import com.burakturker.weatherapp.model.WeatherModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView temperatureTextView, descriptionTextView, town, search_edit;
    private ImageView weatherIconImageView;
    private WeatherApiClient weatherApiClient;
    private FloatingActionButton floating_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_edit = findViewById(R.id.search_edit);
        town = findViewById(R.id.town);
        floating_search = findViewById(R.id.floating_search);
        temperatureTextView = findViewById(R.id.temp);
        descriptionTextView = findViewById(R.id.desc);
        weatherIconImageView = findViewById(R.id.weather_image);
        weatherApiClient = new WeatherApiClient();

        floating_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCityName = search_edit.getText().toString();
                getWeatherData(newCityName);
                town.setText(newCityName);
            }
        });

        // Başlangıç şehir adı
        String cityName = "London";
        getWeatherData(cityName);
    }

    private void getWeatherData(String cityName) {
        Log.d("WeatherAppError", "getWeatherData: Şehir Adı - " + cityName);
        Call<WeatherModel> call = weatherApiClient.getWeatherApi().getWeather(cityName, weatherApiClient.getApiKey());
        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if (response.isSuccessful()) {
                    WeatherModel weatherModel = response.body();
                    if (weatherModel != null) {
                        Log.d("WeatherAppError", "getWeatherData: Başarılı - Weather Model null değil");
                        displayWeatherData(weatherModel);
                    } else {
                        Log.e("WeatherAppError", "getWeatherData: API tarafından null bir WeatherModel döndü");
                    }
                } else {
                    Log.e("WeatherAppError", "getWeatherData: API çağrısı başarılı değil. Hata kodu: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                // Hata durumu
                Log.e("WeatherAppError", "getWeatherData: API çağrısı başarısız. Hata: " + t.getMessage());
            }
        });
    }

    private void displayWeatherData(WeatherModel weatherModel) {
        Log.d("+", "displayWeatherData: Hava Durumu Bilgileri Gösteriliyor");
        // Hava durumu bilgilerini arayüze yerleştirme
        temperatureTextView.setText(weatherModel.getTemperatureCelsius());
        descriptionTextView.setText(weatherModel.getWeatherDescription());

        // Hava durumu koduna göre icon seçimi
        String weatherCode = weatherModel.getWeatherCode();

        // Örneğin, hava durumu kodunu kullanarak bir ikon seçimi yapabilirsiniz.
        // Bu örnek kodda "weatherCode" ile ilgili bir ikon kaynağını seçme işlemi gerçekleştirilmiştir.
        // Bu kısmı projenize uygun olarak özelleştirmeniz gerekebilir.
        int iconResId = getIconResourceByWeatherCode(weatherCode);
        weatherIconImageView.setImageResource(iconResId);
    }

    // Hava durumu koduna göre ikon kaynağını döndüren örnek bir fonksiyon
    private int getIconResourceByWeatherCode(String weatherCode) {
        switch (weatherCode) {
            case "1000":
                return R.drawable.sunny;
            case "1003":
                return R.drawable.partly_cloudy;
            case "1006":
                return R.drawable.cloudy;
            case "1009":
                return R.drawable.overcast;
            case "1030":
                return R.drawable.mist;
            case "1063":
                return R.drawable.patchy_rain_possible;
            case "1066":
                return R.drawable.patchy_snow_possible;
            case "1069":
                return R.drawable.patchy_sleet_possible;
           // case "1072"
            //    return R.drawable.hatalı_foto;
            case "1087":
                return R.drawable.thundery_outbreks_possible;
            case "1114":
                return R.drawable.blowing_snow;
            case "1117":
                return R.drawable.blizzard;
            case "1135":
                return R.drawable.fog;
            case "1147":
                return R.drawable.freezing_fog;
            case "1150":
                return R.drawable.patchy_light_drizzle;
            case "1153":
                return R.drawable.light_drizzle;
            case "1168":
                return R.drawable.freezing_drizzle;
            case "1171":
                return R.drawable.heavy_freezing_drizzle;
            case "1180":
                return R.drawable.patchy_light_rain;
            case "1183":
                return R.drawable.light_rain;
            case "1186":
                return R.drawable.moderate_rain_at_times;
            case "1189":
                return R.drawable.moderate_rain;
            case "1192":
                return R.drawable.heavy_rain_at_times;
            case "1195":
                return R.drawable.heavy_rain;
            case "1198":
                return R.drawable.light_freezing_rain;
            case "1201":
                return R.drawable.moderate_or_heavy_freezing_rain;
            case "1204":
                return R.drawable.light_sleet;
            case "1207":
                return R.drawable.moderate_or_heavy_sleet;
            case "1210":
                return R.drawable.patchy_light_snow;
            case "1213":
                return R.drawable.light_snow;
            case "1216":
                return R.drawable.patchy_moderate_snow;
            case "1219":
                return R.drawable.moderate_snow;
            case "1222":
                return R.drawable.patchy_heavy_snow;
            case "1225":
                return R.drawable.heavy_snow;
            case "1237":
                return R.drawable.ice_pellets;
            case "1240":
                return R.drawable.light_rain_shower;
            case "1243":
                return R.drawable.moderate_or_heavy_rain_shower;
            case "1246":
                return R.drawable.torrential_rain_shower;
            case "1249":
                return R.drawable.light_sleet_showers;
            case "1252":
                return R.drawable.moderate_or_heavy_sleet_showers;
            case "1255":
                return R.drawable.light_snow_showers;
            case "1258":
                return R.drawable.moderate_or_heavy_snow_showers;
            case "1261":
                return R.drawable.light_showers_of_ice_pellets;
            case "1264":
                return R.drawable.moderate_or_heavy_showers_of_ice_pellets;
            case "1273":
                return R.drawable.patchy_light_rain_with_thunder;
            case "1276":
                return R.drawable.moderate_or_heavy_rain_with_thunder;
            case "1279":
                return R.drawable.patchy_light_snow_with_thunder;
            case "1282":
                return R.drawable.moderate_or_heavy_snow_with_thunder;
            default:
                return R.drawable.ic_sunny_day;
        }
    }
}
