package com.burakturker.weatherapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.burakturker.weatherapp.R;
import com.burakturker.weatherapp.adapter.RecyclerViewAdapter;
import com.burakturker.weatherapp.model.WeatherModel;
import com.burakturker.weatherapp.service.WeatherApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ArrayList<WeatherModel> weatherModels;
    private String BASE_URL = "http://api.weatherstack.com/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);


        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        loadData();
    }
    private void loadData(){
        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        Call<List<WeatherModel>> call = weatherApi.getData();
        call.enqueue(new Callback<List<WeatherModel>>() {
            @Override
            public void onResponse(Call<List<WeatherModel>> call, Response<List<WeatherModel>> response) {
                // başarılı olursa
                if (response.isSuccessful()){
                    List<WeatherModel> responseList =response.body();
                    weatherModels = new ArrayList<>(responseList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter = new RecyclerViewAdapter(weatherModels);
                    recyclerView.setAdapter(recyclerViewAdapter);
                }

            }

            @Override
            public void onFailure(Call<List<WeatherModel>> call, Throwable t) {
                //hata olursa
                t.printStackTrace();
            }
        });
    }
}