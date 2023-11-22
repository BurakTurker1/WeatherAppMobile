// MainActivity.java
package com.burakturker.weatherapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.burakturker.weatherapp.R;
import com.burakturker.weatherapp.adapter.RecyclerViewAdapter;
import com.burakturker.weatherapp.model.WeatherModel;
import com.burakturker.weatherapp.service.WeatherApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ArrayList<WeatherModel> weatherModels;
    private final String BASE_URL = "http://api.weatherapi.com/v1/";
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private EditText editTextCity;
    private Button loadButton;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        weatherModels = new ArrayList<>();
        editTextCity = findViewById(R.id.editTextCity);

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter(weatherModels);
        recyclerView.setAdapter(recyclerViewAdapter);

        loadButton = findViewById(R.id.loadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

    private void loadData() {
        if (isLoading) {
            return;
        }

        isLoading = true;

        WeatherApi weatherApi = retrofit.create(WeatherApi.class);

        String cityName = editTextCity.getText().toString();

        if (!cityName.isEmpty()) {
            Call<WeatherModel> call = weatherApi.getData(cityName);
            call.enqueue(new Callback<WeatherModel>() {
                @Override
                public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                    isLoading = false;

                    if (response.isSuccessful()) {
                        WeatherModel weatherModel = response.body();
                        if (weatherModel != null) {
                            weatherModels.clear();
                            weatherModels.add(weatherModel);
                            recyclerViewAdapter.notifyDataSetChanged();
                        } else {
                            Log.e("RetrofitError", "API'den boş veya geçersiz veri döndü.");
                        }
                    } else {
                        Log.e("RetrofitError", "API çağrısı başarısız: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<WeatherModel> call, Throwable t) {
                    isLoading = false;

                    t.printStackTrace();
                    Log.e("RetrofitError", "Retrofit hatası: " + t.getMessage());
                }
            });
        } else {
            isLoading = false;
            Toast.makeText(this, "Lütfen bir şehir adı girin.", Toast.LENGTH_SHORT).show();
        }
    }
}
