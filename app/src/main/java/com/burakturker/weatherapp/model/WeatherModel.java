    package com.burakturker.weatherapp.model;

    import com.google.gson.annotations.SerializedName;

    public class WeatherModel {

        @SerializedName("temp_c")
        public double Derece;

        @SerializedName("wind_mph")
        public double ruzgarHiz;

        @SerializedName("wind_degree")
        public   int ruzgarDerece;

        @SerializedName("pressure_mb")
        public double basinc;

        @SerializedName("humidity")
        public int nem;

        @SerializedName("text")
        public String havaDurumAciklama;

        @SerializedName("name")
        public String isim;

        @SerializedName("icon")
        public String havaDurumIcon;
        public WeatherModel(double derece, double ruzgarHiz, int ruzgarDerece, double basinc, int nem, String havaDurumAciklama, String isim, String havaDurumIcon) {
            this.Derece = derece;
            this.ruzgarHiz = ruzgarHiz;
            this.ruzgarDerece = ruzgarDerece;
            this.basinc = basinc;
            this.nem = nem;
            this.havaDurumAciklama = havaDurumAciklama;
            this.isim = isim;
            this.havaDurumIcon = havaDurumIcon;
        }
    }