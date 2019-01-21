package com.mahan.workshop.weather.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.mahan.workshop.weather.api.WeatherAPI;

import ir.aid.library.Frameworks.helper.ConnectionHelper;
import ir.aid.library.Frameworks.helper.ToolbarHelper;
import ir.aid.library.Frameworks.utils.FrameworkException;
import ir.aid.library.Interfaces.OnGetResponse;
import ir.mft.workshop.weather.R;
import com.mahan.workshop.weather.adapter.WeatherAdapter;

public class ForecastActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        recyclerView = findViewById(R.id.recycler);



        Bundle bundle = getIntent().getExtras();    // Object City Name

        String city = bundle.getString("city"); // catch the city name using intent from MainActivity

        try {    //This is for defining <- (bach sign) in this activity
            new ToolbarHelper(this).Title(city).ButtonHome(true).HomeIcon(R.drawable.icon_arrow_back);
        } catch (FrameworkException e) {
            e.printStackTrace();
        }


        connection(city);
    }

    private void connection(String city){

        String url = "http://phoenix-iran.ir/Files_php_App/WeatherApi/newApiWeather.php";

        new ConnectionHelper(url , 5000)
                .addStringRequest("city" , city)
                .getResponse(new OnGetResponse() {
                    @Override
                    public void notConnection(String result) {
                        // recall if there is no internet
                    }

                    @Override
                    public void success(final String result) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                initWeatherData(result);
                                //Log.i("central_core" , result);
                            }
                        });

                        //  server data fetched in 'resault' String
                    }

                    @Override
                    public void nullable(String result) {
                        //returns null
                    }
                });
    }

    /*
   capturing the data about the city which user entered ofor 10 days using Gson
    */
    private void initWeatherData(String result){

        Gson gson = new Gson();

        WeatherAPI api = gson.fromJson(result , WeatherAPI.class);

        WeatherAdapter weatherAdapter=new WeatherAdapter(this , api);
        recyclerView.setAdapter(weatherAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                ForecastActivity.this,LinearLayout.VERTICAL,false));


    }

    /*
    this method is used for getiing Icons
 */

    private int getIcon(String sky){

        switch (sky){

            case "Cloudy": return R.drawable.icon_cloud;

            default: return 0;
        }

    }



    /*
        this method is used for getiing Icons

     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                finish();
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
