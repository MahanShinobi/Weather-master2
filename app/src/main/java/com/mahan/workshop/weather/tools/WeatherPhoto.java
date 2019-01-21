package com.mahan.workshop.weather.tools;

import ir.mft.workshop.weather.R;

public class WeatherPhoto {

    private static String[] cloudy = {
            "Cloudy" ,
            "tornado" ,
            "tropical storm" ,
            "hurricane" ,
            "foggy" ,
            "cloudy" ,
            "mostly cloudy (night)" ,
            "mostly cloudy (day)" ,
            "partly cloudy (night)" ,
            "partly cloudy (day)" ,
            "partly cloudy"
    };

    private static String[] rainy = {
            "severe thunderstorms" ,
            "thunderstorms" ,
            "mixed rain and hail" ,
            "isolated thunderstorms" ,
            "scattered thunderstorms" ,
            "scattered showers" ,
            "thundershowers" ,
            "isolated thundershowers"
    };

    private static String[] sunny = {
            "cold" ,
            "sunny" ,
            "fair (night)" ,
            "clear (night)" ,
            "fair (day)" ,
            "hot"
    };

    private static String[] wind = {
            "dust" ,
            "haze" ,
            "smoky" ,
            "windy" ,
            "blustery"
    };

    private static String[] snowy = {
            "mixed rain and snow" ,
            "mixed rain and sleet" ,
            "mixed snow and sleet" ,
            "freezing drizzle" ,
            "freezing rain" ,
            "showers" ,
            "snow flurries" ,
            "light snow showers" ,
            "blowing snow" ,
            "snow" ,
            "hail" ,
            "drizzle" ,
            "sleet" ,
            "heavy snow" ,
            "snow showers"
    };

    public static int getPhotoWeather(String text){

        if (isTrue(cloudy , text)){
            return R.drawable.icon_cloud;
        }
        else if (isTrue(rainy , text)){
            return R.drawable.icon_rain;
        }
        else if (isTrue(sunny , text)){
            return R.drawable.icon_sunny;
        }
        else if (isTrue(wind , text)){
            return R.drawable.icon_wind;
        }
        else if (isTrue(snowy , text)){
            return R.drawable.icon_snowy;
        }
        else {
            return R.drawable.icon_snowy;
        }
    }

    private static boolean isTrue(String[] list , String text){

        for(int i = 0; i < list.length; i++){
            boolean isTrue = list[i].equalsIgnoreCase(text);
            if(isTrue){
                return true;
            }
        }

        return false;
    }


    public static int getIcon(String sky){

        //cloudy
        if(sky.equalsIgnoreCase("cloudy")){
            return R.drawable.icon_cloud;
        }
        else if(sky.equalsIgnoreCase("tornado")){
            return R.drawable.icon_cloud;
        }
        else if(sky.equalsIgnoreCase("tropical storm")){
            return R.drawable.icon_cloud;
        }
        else if(sky.equalsIgnoreCase("hurricane")){
            return R.drawable.icon_cloud;
        }
        else if(sky.equalsIgnoreCase("foggy")){
            return R.drawable.icon_cloud;
        }
        else if(sky.equalsIgnoreCase("cloudy")){
            return R.drawable.icon_cloud;
        }
        else if(sky.equalsIgnoreCase("mostly cloudy (night)")){
            return R.drawable.icon_cloud;
        }
        else if(sky.equalsIgnoreCase("mostly cloudy (day)")){
            return R.drawable.icon_cloud;
        }
        else if(sky.equalsIgnoreCase("partly cloudy (night)")){
            return R.drawable.icon_cloud;
        }
        else if(sky.equalsIgnoreCase("partly cloudy (day)")){
            return R.drawable.icon_cloud;
        }
        else if(sky.equalsIgnoreCase("partly cloudy")){
            return R.drawable.icon_cloud;
        }




        //rainy
        else if(sky.equalsIgnoreCase("severe thunderstorms")){
            return R.drawable.icon_rain;
        }
        else if(sky.equalsIgnoreCase("thunderstorms")){
            return R.drawable.icon_rain;
        }
        else if(sky.equalsIgnoreCase("mixed rain and hail")){
            return R.drawable.icon_rain;
        }
        else if(sky.equalsIgnoreCase("isolated thunderstorms")){
            return R.drawable.icon_rain;
        }
        else if(sky.equalsIgnoreCase("scattered thunderstorms")){
            return R.drawable.icon_rain;
        }
        else if(sky.equalsIgnoreCase("scattered showers")){
            return R.drawable.icon_rain;
        }
        else if(sky.equalsIgnoreCase("thundershowers")){
            return R.drawable.icon_rain;
        }
        else if(sky.equalsIgnoreCase("isolated thundershowers")){
            return R.drawable.icon_rain;
        }




        //sunny
        else if(sky.equalsIgnoreCase("cold")){
            return R.drawable.icon_sunny;
        }
        else if(sky.equalsIgnoreCase("sunny")){
            return R.drawable.icon_sunny;
        }
        else if(sky.equalsIgnoreCase("fair (night)")){
            return R.drawable.icon_sunny;
        }
        else if(sky.equalsIgnoreCase("clear (night)")){
            return R.drawable.icon_sunny;
        }
        else if(sky.equalsIgnoreCase("fair (day)")){
            return R.drawable.icon_sunny;
        }
        else if(sky.equalsIgnoreCase("hot")){
            return R.drawable.icon_sunny;
        }




        //wind
        else if(sky.equalsIgnoreCase("dust")){
            return R.drawable.icon_wind;
        }
        else if(sky.equalsIgnoreCase("haze")){
            return R.drawable.icon_wind;
        }
        else if(sky.equalsIgnoreCase("smoky")){
            return R.drawable.icon_wind;
        }
        else if(sky.equalsIgnoreCase("windy")){
            return R.drawable.icon_wind;
        }
        else if(sky.equalsIgnoreCase("blustery")){
            return R.drawable.icon_wind;
        }




        //snowy
        else if(sky.equalsIgnoreCase("mixed rain and snow")){
            return R.drawable.icon_snowy;
        }
        else if(sky.equalsIgnoreCase("mixed rain and sleet")){
            return R.drawable.icon_snowy;
        }
        else if(sky.equalsIgnoreCase("mixed snow and sleet")){
            return R.drawable.icon_snowy;
        }
        else if(sky.equalsIgnoreCase("freezing drizzle")){
            return R.drawable.icon_snowy;
        }
        else if(sky.equalsIgnoreCase("freezing rain")){
            return R.drawable.icon_snowy;
        }
        else if(sky.equalsIgnoreCase("showers")){
            return R.drawable.icon_snowy;
        }
        else if(sky.equalsIgnoreCase("snow flurries")){
            return R.drawable.icon_snowy;
        }
        else if(sky.equalsIgnoreCase("light snow showers")){
            return R.drawable.icon_snowy;
        }
        else if(sky.equalsIgnoreCase("blowing snow")){
            return R.drawable.icon_snowy;
        }
        else if(sky.equalsIgnoreCase("snow")){
            return R.drawable.icon_snowy;
        }
        else if(sky.equalsIgnoreCase("hail")){
            return R.drawable.icon_snowy;
        }
        else if(sky.equalsIgnoreCase("drizzle")){
            return R.drawable.icon_snowy;
        }
        else if(sky.equalsIgnoreCase("sleet")){
            return R.drawable.icon_snowy;
        }
        else if(sky.equalsIgnoreCase("heavy snow")){
            return R.drawable.icon_snowy;
        }
        else if(sky.equalsIgnoreCase("snow showers")){
            return R.drawable.icon_snowy;
        }

        return R.drawable.icon_sunny;

    }

}
