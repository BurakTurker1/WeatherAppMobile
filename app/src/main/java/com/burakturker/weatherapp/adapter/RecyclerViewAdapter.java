package com.burakturker.weatherapp.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.burakturker.weatherapp.R;
import com.burakturker.weatherapp.model.WeatherModel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {
    private ArrayList<WeatherModel> weatherList;
    private String[] BackGround = {"#1e90ff","#9932cc","#00008b"};

    public RecyclerViewAdapter(ArrayList<WeatherModel> weatherList) {
        this.weatherList = weatherList;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
    holder.bind(weatherList.get(position),BackGround,position);
    }

    @Override
    public int getItemCount() {
        //row sayısı
        return weatherList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textTemp;
        ImageView ImgIcon;

        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }
        public  void bind(WeatherModel weatherModel,String[] BackGround,Integer position){
            itemView.setBackgroundColor(Color.parseColor(BackGround[position % 3]));
            textName = itemView.findViewById(R.id.txtViewName);
            textTemp = itemView.findViewById(R.id.txtViewTemp);
            ImgIcon = itemView.findViewById(R.id.imgVievİcon);
            textName.setText(weatherModel.isim);
            textTemp.setText(weatherModel.Derece);
            //ImgIcon.setImageIcon(weatherModel.havaDurumIcon);
        }
    }
}
