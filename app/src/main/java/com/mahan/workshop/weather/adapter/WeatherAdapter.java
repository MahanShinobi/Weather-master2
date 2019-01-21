package com.mahan.workshop.weather.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahan.workshop.weather.api.WeatherAPI;

import java.util.List;

import ir.mft.workshop.weather.R;

import com.mahan.workshop.weather.api.models.Forecast;
import com.mahan.workshop.weather.tools.Converter;
import com.mahan.workshop.weather.tools.WeatherPhoto;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder> {

    private final Context context;
    private WeatherAPI api;
    private int listSize = 10 ;

    public WeatherAdapter(Context context , WeatherAPI api){
        this.context = context;
        this.api = api;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.temp_item, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String city = api.getResult().getLocation().getCity();

        List<Forecast> forecasts = api.getResult().getForecasts();

        String text = forecasts.get(position).getText();
        String day  = forecasts.get(position).getDay();
        int date    = forecasts.get(position).getDate();
        int high    = forecasts.get(position).getHigh();
        int low     = forecasts.get(position).getLow();
        int code    = forecasts.get(position).getCode();


         int tempHigh = Converter.convertToC(high);
         int templow = Converter.convertToC(low);

        holder.txtSky.setText(text);
        holder.txtTemp.setText(tempHigh + " °C   " +templow+" °C");
        holder.txtCityName.setText(day);
        //holder.img.setImageDrawable(R.drawable.background);
        holder.img.setImageDrawable(context.getResources().getDrawable(WeatherPhoto.getIcon(text)));



    }

    @Override
    public int getItemCount() {
        return listSize;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtCityName , txtTemp , txtSky;
        public ImageView img;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCityName = itemView.findViewById(R.id.txtCity);
            txtTemp = itemView.findViewById(R.id.txtTemp);
            txtSky = itemView.findViewById(R.id.txt_sky_is);
            img =itemView.findViewById(R.id.imgWeather);
        }
    }

    private int getIcon(String sky){

        switch (sky){

            //cloudy
            case "Cloudy"                : return R.drawable.icon_cloud;
            case "tornado"               : return R.drawable.icon_cloud;
            case "tropical storm"        : return R.drawable.icon_cloud;
            case "hurricane"             : return R.drawable.icon_cloud;
            case "foggy"                 : return R.drawable.icon_cloud;
            case "cloudy"                : return R.drawable.icon_cloud;
            case "mostly cloudy (night)" : return R.drawable.icon_cloud;
            case "mostly cloudy (day)"   : return R.drawable.icon_cloud;
            case "partly cloudy (night)" : return R.drawable.icon_cloud;
            case "partly cloudy (day)"   : return R.drawable.icon_cloud;
            case "partly cloudy"         : return R.drawable.icon_cloud;


            //rainy
            case "severe thunderstorms"     : return R.drawable.icon_rain;
            case "thunderstorms"            : return R.drawable.icon_rain;
            case "mixed rain and hail"      : return R.drawable.icon_rain;
            case "isolated thunderstorms"   : return R.drawable.icon_rain;
            case "scattered thunderstorms"  : return R.drawable.icon_rain;
            case "scattered showers"        : return R.drawable.icon_rain;
            case "thundershowers"           : return R.drawable.icon_rain;
            case "isolated thundershowers"  : return R.drawable.icon_rain;


            //snowy

            case "mixed rain and snow"  : return R.drawable.icon_snowy;
            case "mixed rain and sleet" : return R.drawable.icon_snowy;
            case "mixed snow and sleet" : return R.drawable.icon_snowy;
            case "freezing drizzle"     : return R.drawable.icon_snowy;
            case "freezing rain"        : return R.drawable.icon_snowy;
            case "showers"              : return R.drawable.icon_snowy;
            case "snow flurries"        : return R.drawable.icon_snowy;
            case "light snow showers"   : return R.drawable.icon_snowy;
            case "blowing snow"         : return R.drawable.icon_snowy;
            case "snow"                 : return R.drawable.icon_snowy;
            case "hail"                 : return R.drawable.icon_snowy;
            case "drizzle"              : return R.drawable.icon_rain;
            case "sleet"                : return R.drawable.icon_snowy;
            case "heavy snow"           : return R.drawable.icon_snowy;
            case "snow showers"         : return R.drawable.icon_snowy;


            //wind
            case "dust"    : return R.drawable.icon_wind;
            case "haze"    : return R.drawable.icon_wind;
            case "smoky"   : return R.drawable.icon_wind;
            case "windy"   : return R.drawable.icon_wind;
            case "blustery": return R.drawable.icon_wind;


            //sunny

            case "cold"         : return R.drawable.icon_sunny;
            case "sunny"        : return R.drawable.icon_sunny;
            case "fair (night)" : return R.drawable.icon_sunny;
            case "clear (night)": return R.drawable.icon_sunny;
            case "fair (day)"   : return R.drawable.icon_sunny;
            case "hot"          : return R.drawable.icon_sunny;

            default: return R.drawable.icon_sunny;
        }

    }


}
