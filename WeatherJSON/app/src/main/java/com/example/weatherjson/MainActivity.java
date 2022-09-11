package com.example.weatherjson;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public static Toast textview6;
    EditText editText;
    static TextView textView6;
    static TextView place;
    static TextView temp;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView6 = findViewById(R.id.textView6);
        //place = findViewById(R.id.place);

    }

    public void getWeather(View view) {

        IIWeather task = new IIWeather();
        task.execute("https://openweathermap.org/data/2.5/weather?q=" + editText.getText().toString() + "&appid=b6907d289e10d714a6e88b30761fae22");
    }

    public void getPlaceTemp(View view) {

        I2Weather task1 = new I2Weather();
        task1.execute("https://openweathermap.org/data/2.5/weather?q="+editText.getText().toString()+"&appid=b6907d289e10d714a6e88b30761fae22");

    }


    }




