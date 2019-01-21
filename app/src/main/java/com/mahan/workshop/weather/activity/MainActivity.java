package com.mahan.workshop.weather.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.mahan.workshop.weather.api.WeatherAPI;

import ir.aid.library.Frameworks.helper.ConnectionHelper;
import ir.aid.library.Frameworks.utils.SharedPreferenceUtils;
import ir.aid.library.Interfaces.OnGetResponse;
import ir.mft.workshop.weather.R;

import com.mahan.workshop.weather.tools.Converter;
import com.mahan.workshop.weather.tools.WeatherPhoto;

public class MainActivity extends AppCompatActivity {


    private MaterialDialog.Builder mBuilder;
    private MaterialDialog mDialog;

    private Button btnFind , btnForecast;
    private TextView txtCityName , txtTemp , txtSky;
    private ImageView img;
    private String city;

    private SharedPreferenceUtils preference;
    private static final String CITY_KET = "city";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preference = new SharedPreferenceUtils(this);

        ConstraintLayout constraintLayout = findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1500);
        animationDrawable.setExitFadeDuration(2500);
        animationDrawable.start();

        initViews();

        if (preference.readString(CITY_KET , "").equals("")){
            connection("Tehran");  //default: tehran
            preference.writeString(CITY_KET, "tehran");
        }
        else {

            city = preference.readString(CITY_KET,"");
            connection(city); // the city witch User entered

        }

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDialog.show(); //for capturing user input --> City

            }
        });

        initDialog();

    }
    /*
    initializing mdialog and get user input
    check if it is empty or not
    */
    private void initDialog(){

        Button btndismiss = (Button) mDialog.findViewById(R.id.btn_dismiss);
        Button btnSave= (Button) mDialog.findViewById(R.id.btn_save);
        final EditText cityEdt = (EditText) mDialog.findViewById(R.id.edt_city) ;
        btndismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cityEdt.getText().toString().equals("")) {
                    cityEdt.setError("is Empty...");
                } else {

                    final String city1= cityEdt.getText().toString();
                    preference.writeString(CITY_KET , city1);   // save the the city in shared preferences


                    Toast.makeText(MainActivity.this,"Successfuly saved!",Toast.LENGTH_LONG).show();

                    mDialog.dismiss();
                    recreate();
                }

            }

    });
    }

    /*
    Defining everything which is in main activity
     */

    private void initViews(){


        btnFind=findViewById(R.id.btnFind);
        txtCityName=findViewById(R.id.txtCityName);
        txtTemp=findViewById(R.id.txtTemp);
        img=findViewById(R.id.img_weather);
        txtSky=findViewById(R.id.txt_sky_is);
        btnForecast=findViewById(R.id.btnForcast);

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //too preference ham bayad zakhire konamesh
            }
        });

        final String location ;


        if (preference.readString(CITY_KET , "").equals("")){
            location = preference.readString(CITY_KET , "tehran");
        }
        else {

            city = preference.readString(CITY_KET,"");
             location = preference.readString(CITY_KET , "");

        }


        // the city name goes to ForecastActivity by clicking btnForecast Button and the ForecastActivity will be started
        btnForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ForecastActivity.class);
                intent.putExtra("city" , location);
                startActivity(intent);
            }
        });

        mBuilder= new MaterialDialog.Builder(this);
        mBuilder.customView(R.layout.find_city_dialog_layout,false);
        mDialog=mBuilder.build();


    }
    /*
    Checking the connection to the server
     */

    private void connection(String city){

        String url = "http://phoenix-iran.ir/Files_php_App/WeatherApi/newApiWeather.php";

        new ConnectionHelper(url , 5000)
                .addStringRequest("city" , city)
                .getResponse(new OnGetResponse() {
                    @Override
                    public void notConnection(String result) {
                        //  if the internet is not connected this method  recalled
                    }

                    @Override
                    public void success(final String result) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (result.equals("null")){
                                    Toast.makeText(MainActivity.this, "not Found", Toast.LENGTH_SHORT).show();
                                }
                                else if (!result.equals("null")){
                                    initWeatherData(result);

                                }
                                //Log.i("central_core" , result);
                            }
                        });

                        //  information from the server will be saved in 'resault' String
                    }

                    @Override
                    public void nullable(String result) {
                        // this method does nothing! :D
                    }
                });
    }

    /*
    capturing the data about the city which user entered on this day using Gson
     */
    private void initWeatherData(String result){

        Gson gson = new Gson();

        try{
            WeatherAPI api = gson.fromJson(result , WeatherAPI.class);

            int conditionTemp = api.getResult().getCondition().getTemperature();
            int temp = Converter.convertToC(conditionTemp);




            String city = api.getResult().getLocation().getCity();
            String skyText = api.getResult().getCondition().getText();
            if (city.equals("")){
                city = "tehran" ;
            }
            txtCityName.setText(city);
            txtTemp.setText(String.valueOf(temp) + " °C");
            txtSky.setText(skyText);


            img.setImageDrawable(getResources().getDrawable(WeatherPhoto.getPhotoWeather(skyText)));
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.d("central_core", e.toString());
            Toast.makeText(this, "check other city", Toast.LENGTH_LONG).show();
        }

    }

}
