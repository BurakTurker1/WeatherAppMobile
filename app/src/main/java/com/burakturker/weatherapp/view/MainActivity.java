package com.burakturker.weatherapp.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.burakturker.weatherapp.R;
import com.burakturker.weatherapp.adapter.WeatherApiClient;
import com.burakturker.weatherapp.model.WeatherModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView temperatureTextView, descriptionTextView;
    private ImageView weatherIconImageView;
    private WeatherApiClient weatherApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temperatureTextView = findViewById(R.id.temp);
        descriptionTextView = findViewById(R.id.desc);
        weatherIconImageView = findViewById(R.id.weather_image);
        weatherApiClient = new WeatherApiClient();

        // Kullanıcıdan alınan şehir adı, burada örneğin "London" olarak alınmıştır.
        String cityName = "London";

        // Hava durumu bilgilerini çekmek için API isteği yapılır.
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
        Log.d("WeatherAppError", "displayWeatherData: Hava Durumu Bilgileri Gösteriliyor");
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
            case "1003": // Örnek olarak "1003" kodu için bir ikon
                return R.drawable.ic_partly_cloudy;
            // Diğer hava durumu kodları için gerekli case'leri ekleyebilirsiniz.
            // Örnek olarak "ic_weather_sunny" yerine, projenizde kullanacağınız gerçek ikon kaynaklarını ekleyin.
            default:
                return R.drawable.ic_sunny_day; // Varsayılan ikon
        }
    }
}
