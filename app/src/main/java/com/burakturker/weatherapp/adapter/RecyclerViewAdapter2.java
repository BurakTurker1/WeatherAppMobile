package com.burakturker.weatherapp.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.burakturker.weatherapp.model.WeatherModel;

import java.util.ArrayList;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.WeatherHolder> {

    private ArrayList<WeatherModel> weatherList;
    public RecyclerViewAdapter2(ArrayList<WeatherModel> weatherList) {
        this.weatherList = weatherList;
    }




    @NonNull
    @Override
    public WeatherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public class WeatherHolder extends RecyclerView.ViewHolder{

        public WeatherHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
