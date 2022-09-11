package com.example.weatherjson;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class I2Weather extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String... urls) {
        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data = reader.read();
            while (data != -1) {
                char current = (char) data;
                result += current;
                data = reader.read();
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {


            JSONObject jsonObject = new JSONObject(s);
            JSONObject main = new JSONObject(jsonObject.getString("main"));
            JSONObject wind = new JSONObject(jsonObject.getString("wind"));
            JSONObject cloud = new JSONObject(jsonObject.getString("clouds"));

            String string = "";


            String temperature = main.getString("temp");
            Float temp = (Float) (Float.valueOf(temperature));
            int temp2 = Math.round(temp);
            String Dtemp = Integer.toString(temp2);
            String placename = jsonObject.getString("name");
            String pressure = main.getString("pressure");
            String speed = wind.getString("speed");
            String all = cloud.getString("all");


            if (!Dtemp.equals("") && !placename.equals("") && !pressure.equals("") && !speed.equals("") && !all.equals("")) {
                string += placename + ": temperature = " + Dtemp+ "`C" + ": Pressure = "+ pressure+"mbar" + ": wind Speed = "+ speed +"km/h" +": Number of clouds : "+ all +"\n";
            }



            if (!string.equals("")) {
                MainActivity.textView6.setText(string);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
